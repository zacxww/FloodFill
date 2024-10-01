import FloodFill.FloodFill;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) {
        try {
            File inputFile = new File("/FloodFill/imagens/imagem.jpg"); // CAMINHO
            BufferedImage image = ImageIO.read(inputFile);

            // scanner para ler a escolha
            try (Scanner scanner = new Scanner(System.in)) {
                // escolha do usuário: pilha ou fila
                System.out.println("Deseja usar Pilha ou Fila? Digite 'pilha' ou 'fila':");
                String escolha = scanner.nextLine().toLowerCase();
                boolean usarPilha = escolha.equals("pilha");

                int startRow = 50; // LINHA
                int startCol = 100; // COLUNA
                Color newColor = Color.YELLOW; // COR

                // Verifique se as coordenadas estão dentro dos limites
                if (startRow < 0 || startRow >= image.getHeight() || startCol < 0 || startCol >= image.getWidth()) {
                    System.out.println("Coordenadas de início estão fora dos limites da imagem.");
                    return;
                }

                FloodFill floodFill = new FloodFill();
                floodFill.floodFillImage(image, startRow, startCol, newColor, usarPilha);
            }

            // salva a imagem
            File outputFile = new File("/FloodFill/imagens/imagem-modificada.jpg");
            ImageIO.write(image, "jpg", outputFile);

            System.out.println("Imagem processada com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao processar a imagem: " + e.getMessage());
        }
    }
}
