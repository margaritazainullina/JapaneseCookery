package result;

import java.io.*;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsConstants;
import org.apache.struts2.StrutsException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.util.TextParseUtil;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import org.apache.struts2.views.xslt.AdapterFactory;
import org.apache.struts2.views.xslt.ServletURIResolver;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XSLTResult implements Result {
    private static org.apache.log4j.Logger LOG4j = org.apache.log4j.Logger.getLogger("common");
    private static final long serialVersionUID = 6424691441777176763L;

    /** Log instance for this result. */
    private static final Logger LOG = LoggerFactory.getLogger(XSLTResult.class);

    /** 'stylesheetLocation' parameter.  Points to the xsl. */
    public static final String DEFAULT_PARAM = "stylesheetLocation";

    /** Cache of all tempaltes. */
    private static final Map<String, Templates> templatesCache;

    static {
        templatesCache = new HashMap<String, Templates>();
    }

    // Configurable Parameters
    /** Determines whether or not the result should allow caching. */
    protected boolean noCache;
    /** Indicates the location of the xsl template. */
    private String stylesheetLocation;
    /** Indicates the property name patterns which should be exposed to the xml. */
    private String matchingPattern;
    /** Indicates the property name patterns which should be excluded from the xml. */
    private String excludingPattern;
    /** Indicates the ognl expression respresenting the bean which is to be exposed as xml. */
    private String exposedValue;
    private boolean parse;
    private AdapterFactory adapterFactory;

    public XSLTResult() {}

    public XSLTResult(String stylesheetLocation) {
        this();
        setStylesheetLocation(stylesheetLocation);
    }
    
    @Inject(StrutsConstants.STRUTS_XSLT_NOCACHE)
    public void setNoCache(String val) {
        noCache = "true".equals(val);
    }

    /**
     * @deprecated Use #setStylesheetLocation(String)
     */
    public void setLocation(String location) {
        setStylesheetLocation(location);
    }

    public void setStylesheetLocation(String location) {
        if (location == null) throw new IllegalArgumentException("Null location");
        this.stylesheetLocation = location;
    }

    public String getStylesheetLocation() {
        return stylesheetLocation;
    }

    public String getExposedValue() {
        return exposedValue;
    }

    public void setExposedValue(String exposedValue) {
        this.exposedValue = exposedValue;
    }

    /**
     * @deprecated Since 2.1.1
     */
    public String getMatchingPattern() {
        return matchingPattern;
    }

    /**
     * @deprecated Since 2.1.1
     */
    public void setMatchingPattern(String matchingPattern) {
        this.matchingPattern = matchingPattern;
    }

    /**
     * @deprecated Since 2.1.1
     */
    public String getExcludingPattern() {
        return excludingPattern;
    }

    /**
     * @deprecated Since 2.1.1
     */
    public void setExcludingPattern(String excludingPattern) {
        this.excludingPattern = excludingPattern;
    }

    /**
     * If true, parse the stylesheet location for OGNL expressions.
     * @param parse
     */
    public void setParse(boolean parse) {
        this.parse = parse;
    }

    public void execute(ActionInvocation invocation) throws Exception {
        long startTime = System.currentTimeMillis();
        String location = getStylesheetLocation();

        if (parse) {
            ValueStack stack = ActionContext.getContext().getValueStack();
            location = TextParseUtil.translateVariables(location, stack);
        }

        try {
            HttpServletResponse response = ServletActionContext.getResponse();

            PrintWriter writer = response.getWriter();

            // Create a transformer for the stylesheet.
            Templates templates = null;
            Transformer transformer;
            if (location != null) {
                templates = getTemplates(location);
                transformer = templates.newTransformer();
            } else transformer = TransformerFactory.newInstance().newTransformer();

            transformer.setURIResolver(getURIResolver());
            transformer.setErrorListener(new ErrorListener() {
                public void error(TransformerException exception)
                        throws TransformerException {
                    throw new StrutsException("Error transforming result", exception);
                }

                public void fatalError(TransformerException exception) throws TransformerException {
                    throw new StrutsException("Fatal error transforming result", exception);
                }

                public void warning(TransformerException exception) throws TransformerException {
                    if (LOG.isWarnEnabled()) LOG.warn(exception.getMessage(), exception);
                }
            });

            String mimeType;
            if (templates == null) mimeType = "text/xml"; // no stylesheet, raw xml
            else mimeType = templates.getOutputProperties().getProperty(OutputKeys.MEDIA_TYPE);
            if (mimeType == null) {
                // guess (this is a servlet, so text/html might be the best guess)
                mimeType = "text/html";
            }

            response.setContentType(mimeType);

            Object result = invocation.getAction();
            if (exposedValue != null) {
                ValueStack stack = invocation.getStack();
                result = stack.findValue(exposedValue);
            }

            Source xmlSource = getDOMSourceForStack(result);

            // added by budi
            printDOMSource((DOMSource) xmlSource);
            // ----end -----            
            
            // Transform the source XML to System.out.
            if (LOG.isDebugEnabled()) {
        	LOG.debug("xmlSource = " + xmlSource);
            }
            transformer.transform(xmlSource, new StreamResult(writer));

            writer.flush(); // ...and flush...

            if (LOG.isDebugEnabled()) {
                LOG.debug("Time:" + (System.currentTimeMillis() - startTime) + "ms");
            }
        } catch (Exception e) {
            LOG.error("Unable to render XSLT Template, '" + location + "'", e);
            throw e;
        }
    }

    protected AdapterFactory getAdapterFactory() {
        if (adapterFactory == null) adapterFactory = new AdapterFactory();
        return adapterFactory;
    }

    protected void setAdapterFactory(AdapterFactory adapterFactory) {
        this.adapterFactory = adapterFactory;
    }

    /**
     * Get the URI Resolver to be called by the processor when it encounters an xsl:include, xsl:import, or document()
     * function. The default is an instance of ServletURIResolver, which operates relative to the servlet context.
     */
    protected URIResolver getURIResolver() {
        return new ServletURIResolver(
                ServletActionContext.getServletContext());
    }

    protected Templates getTemplates(String path) throws TransformerException, IOException {
        String pathFromRequest = ServletActionContext.getRequest().getParameter("xslt.location");

        if (pathFromRequest != null) path = pathFromRequest;

        if (path == null) throw new TransformerException("Stylesheet path is null");

        Templates templates = templatesCache.get(path);

        if (noCache || (templates == null)) {
            synchronized (templatesCache) {
                URL resource = ServletActionContext.getServletContext().getResource(path);

                if (resource == null) {
                    throw new TransformerException("Stylesheet " + path + " not found in resources.");
                }

                if (LOG.isDebugEnabled()) {
                    LOG.debug("Preparing XSLT stylesheet templates: " + path);
                }

                TransformerFactory factory = TransformerFactory.newInstance();
                factory.setURIResolver(getURIResolver());
                templates = factory.newTemplates(new StreamSource(resource.openStream()));
                templatesCache.put(path, templates);
            }
        }
        return templates;
    }

    protected Source getDOMSourceForStack(Object value) throws IllegalAccessException, InstantiationException {
        return new DOMSource(getAdapterFactory().adaptDocument("result", value) );
    }
    // added by budi
    private void printDOMSource(DOMSource source) {
        StringBuilder sb = new StringBuilder(1024);
        Node node = source.getNode();
        printNode(node, sb, 0);
        LOG4j.info(sb.toString());
    }
    
    private void printNode(Node node, StringBuilder sb, int indent) {
        String nodeName = node.getNodeName();

        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < indent; i++) spaces.append("    ");
        
        sb.append(spaces);
        sb.append("<" + nodeName + ">\n");
        
        if (node.getNodeType() == Node.TEXT_NODE) {
            sb.append(spaces).append("    ").append(node.getNodeValue() + "\n");
        }

        NodeList nodeList = node.getChildNodes();
        int childCount = nodeList.getLength();
        for (int i = 0; i < childCount; i++) {
            Node childNode = nodeList.item(i);
            printNode(childNode, sb, indent + 1);
        }
        sb.append(spaces);
        sb.append("</" + nodeName + ">\n");
    }    
}