package com.example.JOBSHOP.JOBSHOP;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageReader {

    public static void main(String[] args) {
        try {
            // Specify the path to your image file
            String imagePath = "C:\\Users\\moeha\\OneDrive\\Pictures\\myPic.jpg";

            // Read image data from file
            byte[] imageData = readImageDataFromFile(imagePath);

            // Display the byte data
            System.out.println("Image Data: " + imageData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read image data from file
    private static byte[] readImageDataFromFile(String imagePath) throws IOException {
        // Create a file object for the image
        File imageFile = new File(imagePath);

        // Create a byte array to hold image data
        byte[] imageData = new byte[(int) imageFile.length()];

        // Create input stream to read from the file
        try (FileInputStream fis = new FileInputStream(imageFile)) {
            // Read image data into byte array
            fis.read(imageData);
        }

        // Return the image data
        return imageData;
    }
}
