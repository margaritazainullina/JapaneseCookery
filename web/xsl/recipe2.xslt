<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >
    <xsl:output method="html" omit-xml-declaration="yes" version="4.0" encoding="UTF-8" indent="yes"/>
    <xsl:template match="result">
        <table border="0px" >
            <tr>
                <td class="recipetitle">
                    <xsl:value-of select="recipeName"/>
                </td>                
            </tr>
            <tr>
                <td>
                    <xsl:value-of select="info"/>
                </td>
            </tr>
            <tr>
                <td>
                    <xsl:value-of select="' '"/>
                </td>
            </tr>
            
            <xsl:if test="ingredients/item/content">            
                
                <tr>
                    <td class="recipetitle2">
                        <xsl:text>Ингридиенты</xsl:text>
                    </td>
                </tr>            
                <xsl:for-each select="ingredients/item">
                    <tr>
                        <td> 
                            <xsl:value-of select="content"/>
                            <xsl:text> </xsl:text> 
                        </td> 
                        <td> 
                            <xsl:value-of select="amount"/>
                            <xsl:text> </xsl:text> 
                            <xsl:value-of select="unit"/>
                        </td>
                    </tr>
                </xsl:for-each>
            </xsl:if>
            <xsl:if test="steps/item">
                <tr>
                    <td class="recipetitle2">
                        <xsl:text>Приготовление</xsl:text>
                    </td>
                </tr>                
                <xsl:for-each select="steps/*">
                    <tr>
                        <td class="step">
                            <xsl:text>Шаг </xsl:text>
                            <xsl:value-of select="position()"/>
                        </td>
                    </tr>                    
                    <tr>
                        <td>
                            <xsl:value-of select="."/>
                        </td>
                    </tr>
                </xsl:for-each> 
            </xsl:if>
        </table>
    </xsl:template>
</xsl:stylesheet>