package ece.fr.controller;


import java.io.*;

interface Serializer {
    static void serialize(Object object, String path) {

        // Serialization
        try {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(object);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        } catch (IOException ex) {
            System.out.println("IOException is caught " + ex);
        }
    }

    static Object deSerialize(Object object, String path) {
// Deserialization
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            object = in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");


        } catch (IOException ex) {
            System.out.println("IOException is caught1");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
        return object;
    }
}