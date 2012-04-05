<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >
    <xsl:output method="html" omit-xml-declaration="yes" version="4.0" encoding="UTF-8" indent="yes"/>
    <xsl:template match="root">
        <img src="heap/ella001.jpg" width="160">ella</img>
        <table border="0px" >
            <tr>
                <td class="recipetitle">
                    <xsl:value-of select="name"/>
                </td>                
            </tr>
            <tr>
                <td>
                    <xsl:value-of select="info"/>
                </td>
            </tr>
            <tr>
                <td class="recipetitle2">
                    <xsl:text>Ингридиенты</xsl:text>
                </td>
            </tr>            
            <xsl:for-each select="prepare/ingredient">
                <tr>
                    <td> 
                        <xsl:value-of select="."/>
                        <xsl:text> </xsl:text> 
                    </td> 
                    <td> 
                        <xsl:value-of select="@amount"/>
                        <xsl:text> </xsl:text> 
                        <xsl:value-of select="@unit"/>
                    </td>
                </tr>
            </xsl:for-each>
            <tr>
                <td class="recipetitle2">
                    <xsl:text>Приготовление</xsl:text>
                </td>
            </tr>                
            <xsl:for-each select="cook/step">
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
        </table>
    </xsl:template>
</xsl:stylesheet>