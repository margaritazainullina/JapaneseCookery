<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >
    <xsl:output method="html" omit-xml-declaration="yes" version="4.0" encoding="UTF-8" indent="yes"/>
    <xsl:template match="result">
        <table border="0px">
            <tr>
                <th><xsl:value-of select="recipeName"/><xsl:text> </xsl:text></th>                
                <th><xsl:value-of select="info"/></th>
            </tr>
            <xsl:if test="ingredients/item/content">            
                <tr><td><xsl:text>Ингридиенты</xsl:text></td></tr>            
                <xsl:for-each select="ingredients/item">
                    <tr>
                        <td>
                            <xsl:value-of select="amount"/>
                            <xsl:text> </xsl:text>
                            <xsl:value-of select="unit"/>
                            <xsl:text> </xsl:text>
                            <xsl:value-of select="content"/>
                        </td>
                    </tr>
                </xsl:for-each>
            </xsl:if>
            <xsl:if test="steps/item">
                <tr><td><xsl:text>Приготовление</xsl:text></td></tr>                
                <xsl:for-each select="steps/*">
                    <tr>
                        <td><xsl:text>Шаг </xsl:text><xsl:value-of select="position()"/></td>
                    </tr>                    
                    <tr>
                        <td><xsl:value-of select="."/></td>
                    </tr>
                </xsl:for-each> 
            </xsl:if>
            <xsl:if test="image">
                <tr><td><xsl:text>Есть фото!</xsl:text></td></tr>                
            </xsl:if>            
        </table>
    </xsl:template>
</xsl:stylesheet>