package com.example.dm2.examen_febrero;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RssHandler extends DefaultHandler{


    private Tiempo tiempoActual;
    private StringBuilder sbTexto;
    private boolean aux=true;

    public Tiempo getTiempoActual() {
        return tiempoActual;
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        super.characters(ch, start, length);
        if (this.tiempoActual != null)
            sbTexto.append(ch, start, length);
    }
    @Override
    public void endElement(String uri, String localName, String name)
            throws SAXException {
        super.endElement(uri, localName, name);
        if (this.tiempoActual != null&&aux) {
            if (localName.equals("hora_datos")) {
                tiempoActual.setHora("Hora: "+sbTexto.toString());
            } else if (localName.equals("temperatura")) {
                tiempoActual.setTemp("Temperatura: "+sbTexto.toString());
            }
            else if (localName.equals("texto")) {
                tiempoActual.setEstado("Estado del cielo: "+sbTexto.toString());
                aux=false;
            }

            sbTexto.setLength(0);
        }
    }
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();

        sbTexto = new StringBuilder();
    }
    @Override
    public void startElement(String uri, String localName,
                             String name, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, name, attributes);
        if (localName.equals("datos")) {
            tiempoActual = new Tiempo();
        }
    }

}

