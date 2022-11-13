package ece.fr.controller;


import java.io.*;

interface Serializer {
    static void serialize(Object object, String fileName) {
        String ressourcePath = "src/main/resources/";
        String filePath = ressourcePath + fileName;

        // Serialization
        try {
            //create directory if not exist
            File directory = new File(ressourcePath);
            if (!directory.exists()) {
                directory.mkdir();
            }


            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(object);

            out.close();
            file.close();


        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }

    static Object deSerialize(Object object, String fileName) {
        String ressourcePath = "src/main/resources/";
        String filePath = ressourcePath + fileName;

        // Deserialization
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            object = in.readObject();

            in.close();
            file.close();


        } catch (IOException ex) {
            System.out.println("partie introuvable");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
        return object;
    }
}