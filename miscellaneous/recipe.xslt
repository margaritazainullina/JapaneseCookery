<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >
<xsl:output method="html" omit-xml-declaration="yes" version="4.0" encoding="UTF-8" indent="yes"/>
    
    <xsl:template match="/root">
        <html>
			<head>
				<title>Клуб рецептов японской кухни</title>
				<link rel="stylesheet" type="text/css" href="style.css"/>
			</head>
            <body>
             <xsl:value-of select="ingredient"/>
             <xsl:apply-templates />
            </body>
        </html>
    </xsl:template>
    
    <xsl:template match="info">
        <h1><p><xsl:value-of select="."/></p> </h1>
    </xsl:template>	
   
	<xsl:template match="prepare">
		<p>
		<xsl:apply-templates select="ingredient"/>
		<br/>	
		</p>
	</xsl:template>	   
   
    <xsl:template match="ingredient">
    	<xsl:value-of select="."/>
    	<xsl:text> </xsl:text>
    	<xsl:value-of select="@amount"/>	
		<xsl:text> </xsl:text>
		<xsl:value-of select="@unit"/>
		<br/>
	</xsl:template>
	
		
	 <xsl:template match="step">
		<xsl:value-of select="."/>
		<br/>
    </xsl:template>
    
</xsl:stylesheet>