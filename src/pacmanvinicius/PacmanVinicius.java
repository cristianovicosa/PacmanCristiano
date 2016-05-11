/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanvinicius;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author viniciusduarte
 */
public class PacmanVinicius extends base.Jogo {

    //Objeto para armazenar o PNG do pacman
    BufferedImage pacman;

    //Tamanho fixo de cada boneco, em pixels.
    final int TAM = 28;

    //Quadro indicador de qual boneco pegar a cada momento
    int quadro;

    //Posição x e y, em pixels, que o pacman estará a cada momento
    int x, y;

    //Direcao para a qual o pacman vai.
    int direcao;

    //Altura real (Precisa disso no MAC, talvez em outros OS não precise.
    int alturaReal;
    
    //Correspondência de posição no mapa com pixels na tela: 
    //cada posição tem quantos pixels?
    final int MAPA_X_PIXEL = 2;

    public static void main(String[] args) {
        base.JogoApp.inicia(new PacmanVinicius());
    }

    public PacmanVinicius() {
        titulo = "Pacman do Vinícius";
        atraso = 2;
        
        /*
        ATENÇÃO ESSA DIFERENCIAÇÃO DE ALTURA E ALTURA REAL TALVEZ NÃO SEJA
        NECESSÁRIA. CASO NÃO SEJA, APAGUE O CÓDIGO E FAÇA TUDO EM TERMOS DE
        ALTURA APENAS.        
        */
        alturaReal = altura;
        altura += 22;

        try {
            pacman = ImageIO.read(new File("imagens/pacman.png"));
        } catch (IOException ex) {
            Logger.getLogger(PacmanVinicius.class.getName()).log(Level.SEVERE, null, ex);
        }

        quadro = 0;
        x = y = 0;
        //Começa indo para a direita.
        direcao = KeyEvent.VK_RIGHT;
    }

    @Override
    public void inicia() {

    }

    @Override
    public void desenha(Graphics2D g) {
        g.drawImage(pacman.getSubimage(quadro * 30, (direcao - 37) * 30, TAM, TAM),
                x, y, null);
    }

    @Override
    public void atualiza() {
        //atualização do quadro
        quadro++;
        if (quadro == 3) {
            quadro = 0;
        }

        switch (direcao) {
            //Vai andar de 1 em 1 pixel, por padrão.
            case KeyEvent.VK_RIGHT:
                if (x < largura - TAM) {
                    x++;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (x > 0) {
                    x--;
                }
                break;
                case KeyEvent.VK_DOWN:
                if (y < alturaReal - TAM) {
                    y++;
                }
                break;
                    case KeyEvent.VK_UP:
                if (y > 0) {
                    y--;
                }
                break;
        }
    }

    //Método para responder ao teclado, somente setas.
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key > 36 && key < 41) {
            direcao = key;
        }
    }

}
