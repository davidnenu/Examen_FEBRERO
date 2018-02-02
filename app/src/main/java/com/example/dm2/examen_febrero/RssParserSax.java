package com.example.dm2.examen_febrero;

import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class RssParserSax {
    private URL rssUrl;
    public RssParserSax(String url) {
        try {
            this.rssUrl = new URL(url);
        }
        catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    public Tiempo parse() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            RssHandler handler = new RssHandler();
            InputSource is = new InputSource (this.getInputStream());
            is.setEncoding("ISO-8859-1");
            parser.parse(is, handler);
            return handler.getTiempoActual();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private InputStream getInputStream() {
        try {
            return rssUrl.openConnection().getInputStream();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
