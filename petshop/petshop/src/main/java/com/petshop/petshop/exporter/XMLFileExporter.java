package com.petshop.petshop.exporter;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

@Component
public class XMLFileExporter {
    public String exportData(Object object) {
        String xmlContent = null;
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();

            jaxbMarshaller.marshal(object, sw);

            xmlContent = removeXmlDeclaration(sw.toString());

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return xmlContent;
    }

    private String removeXmlDeclaration(String xmlContent) {
        int declarationEnd = xmlContent.indexOf("?>");
        if (declarationEnd != -1) {
            return xmlContent.substring(declarationEnd + 2).trim();
        }
        return xmlContent;
    }
    public void writeToFile(String xmlContent, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(xmlContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
