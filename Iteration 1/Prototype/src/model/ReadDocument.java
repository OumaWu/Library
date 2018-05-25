package model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ReadDocument {
	
	private static void writeRoman(StringBuilder sb, Roman doc) {
		// titre, auteur, nbPages, prixLitt
		sb.append(doc.getClass().getSimpleName() + ','
				+ doc.getNumEnreg() + ','
				+ doc.getTitre() + ','
				+ doc.getAuteur() + ','
				+ doc.getNbPages() + ','
				+ doc.getPrixLitteraire());
		sb.append('\n');
	}
	
	private static void writeManuel(StringBuilder sb, Manuel doc) {
		sb.append(doc.getClass().getSimpleName() + ','
				+ doc.getNumEnreg() + ','
				+ doc.getTitre() + ','
				+ doc.getAuteur() + ','
				+ doc.getNbPages() + ','
				+ doc.getNiveau());
		sb.append('\n');
	}

	private static void writeRevue(StringBuilder sb, Revue doc) {
		sb.append(doc.getClass().getSimpleName() + ','
				+ doc.getNumEnreg() + ','
				+ doc.getTitre() + ','
				+ doc.getMois() + ','
				+ doc.getAnnee());
		sb.append('\n');
	}
	
	public static void write(String filename, List<Document> documents) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File(filename));
        StringBuilder sb = new StringBuilder();
        for (Document doc : documents) {
        	if (doc instanceof Roman) {
        		writeRoman(sb, (Roman) doc);
        	}
        	else if (doc instanceof Manuel) {
        		writeManuel(sb, (Manuel) doc);
        	}
        	else if (doc instanceof Revue) {
        		writeRevue(sb, (Revue) doc);
        	}
        }

        pw.write(sb.toString());
        pw.close();
        System.out.println("done!");
	}
	
	public static List<Document> read(String filename) {

        List<Document> docs = new ArrayList<Document>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        	
        	String line = "";
        	Document doc = null;
            while ((line = br.readLine()) != null) {

                String[] document = line.split(",");
                switch (document[0].toUpperCase())
                {
                	case "ROMAN" :
                		doc = new Roman(Integer.parseInt(document[1]),
                				document[2], document[3],
	                			Integer.parseInt(document[4]),
	                			Integer.parseInt(document[5]));
	                	
	                	break;
                	case "MANUEL" :
                		doc = new Manuel(Integer.parseInt(document[1]),
                				document[2], document[3],
	                			Integer.parseInt(document[4]),
	                			Integer.parseInt(document[5]));
	                	break;
                	case "REVUE" :
                		doc = new Revue(Integer.parseInt(document[1]),
                				document[2],
	                			Integer.parseInt(document[3]),
	                			Integer.parseInt(document[4]));
	                	break;
                	default :
                		break;
                }
                docs.add(doc);
            }
//            System.out.println(docs);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return docs;
	}
}
