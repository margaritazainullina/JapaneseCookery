<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >
    <xsl:output method="html" omit-xml-declaration="yes" version="4.0" encoding="UTF-8" indent="yes"/>
    <xsl:template match="result">
        <table>
            <tr>
                <th><xsl:value-of select="info"/></th>
            </tr>
            <xsl:for-each select="ingredients/*">
                <tr>
                    <td><xsl:value-of select="."/></td>
                </tr>
            </xsl:for-each>
            <xsl:for-each select="steps/*">
                <tr>
                    <td><xsl:value-of select="."/></td>
                </tr>
            </xsl:for-each>            
        </table>
    </xsl:template>
</xsl:stylesheet>