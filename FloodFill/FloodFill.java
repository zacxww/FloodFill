package FloodFill;

import estruturas.Fila;
import estruturas.Pilha;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FloodFill {

    private class Pixel {
        int row, col;

        Pixel(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private boolean isSafe(int r, int c, int width, int height) {
        return (r >= 0 && r < height && c >= 0 && c < width);
    }

    public void floodFillImage(BufferedImage image, int sr, int sc, Color newColor, boolean usarPilha) {
        int originalColor = image.getRGB(sc, sr);
        int newRGB = newColor.getRGB();

        // Se a cor original é igual à nova cor, não faz nada
        if (originalColor == newRGB) {
            return;
        }

        System.out.println("Cor original (RGB): " + originalColor + ", Nova cor (RGB): " + newRGB);

        int width = image.getWidth();
        int height = image.getHeight();

        // Estruturas
        Pilha<Pixel> pilha = new Pilha<>();
        Fila<Pixel> fila = new Fila<>();

        // Adiciona o pixel inicial
        if (usarPilha) {
            pilha.push(new Pixel(sr, sc));
        } else {
            fila.enqueue(new Pixel(sr, sc));
        }

        // Direções para mover (norte, sul, leste, oeste)
        int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        // JFrame
        JFrame frame = new JFrame("Flood Fill Animation");
        JLabel label = new JLabel(new ImageIcon(image));
        frame.getContentPane().add(label);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Enquanto houver pixels na estrutura
        while ((usarPilha && !pilha.isEmpty()) || (!usarPilha && !fila.isEmpty())) {
            Pixel current;
            if (usarPilha) {
                current = pilha.pop();  // Usando pilha
            } else {
                current = fila.dequeue();  // Usando fila
            }

            int r = current.row;
            int c = current.col;

            // Aplica a nova cor
            image.setRGB(c, r, newRGB);

            // Atualiza o JLabel com a nova imagem
            label.setIcon(new ImageIcon(image));
            frame.repaint(); // Atualiza a janela

            // Verifica os vizinhos
            for (int[] dir : directions) {
                int newR = r + dir[0];
                int newC = c + dir[1];

                // Se o novo pixel está dentro dos limites e tem a cor original
                if (isSafe(newR, newC, width, height) && image.getRGB(newC, newR) == originalColor) {
                    if (usarPilha) {
                        pilha.push(new Pixel(newR, newC));
                    } else {
                        fila.enqueue(new Pixel(newR, newC));
                    }
                }
            }

            // Espera um curto período de tempo antes de continuar
            try {
                Thread.sleep(50); // Ajuste o valor aqui para acelerar ou desacelerar
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        frame.dispose();
    }
}
