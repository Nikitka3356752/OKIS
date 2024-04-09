package org.example;

import java.io.File;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;

public class Obast_and_tochka {
    public static void main(String[] args) throws Exception {
        Scanner mc = new Scanner(System.in);

        System.out.print("Введите путь: ");
        String file1 = mc.nextLine();


        System.out.println("Введи кол-во точек");
        int n = mc.nextInt();
        VIVOD(file1, n);
    }

    public static void VIVOD(String file1, int n) throws  Exception{
        readFile(file1);
        FileWriter fwr1 = new FileWriter(file1);
        Random random = new Random();
        Integer [][] mas  = new Integer [2][n];

        for (int i = 0; i < n; i++) {
            mas[0][i] = random.nextInt(9 - (-4)) + (-4);
            mas[1][i] = random.nextInt(7 - (-4)) + (-4);
            int cnt = i + 1;
            fwr1.write(cnt + ")\tx)" + mas[0][i] + "\ty)" + mas[1][i] + "\r\n");
        }
        fwr1.write("\nВывод\n\n");
        for (int i = 0; i < n; i++) {
            int pop = FIGURE(mas[0][i], mas[1][i]);
            int cnt = i + 1;
            if (pop == 2) {
                fwr1.write(cnt + ") Точка (" + mas[0][i] + ";" + mas[1][i] + ")\tПопала в область фигуры 2\r\n");
            }
            if (pop == 1) {
                fwr1.write(cnt + ") Точка (" + mas[0][i] + ";" + mas[1][i] + ")\tПопала в область фигуры 1\r\n");
            }
            if (pop == 0) {
                fwr1.write(cnt + ") Точка (" + mas[0][i] + ";" + mas[1][i] + ")\tНе попала в область\r\n");
            }
            if (pop == -1) {
                fwr1.write(cnt + ") Точка (" + mas[0][i] + ";" + mas[1][i] + ")\tПопала на границу фигуры 1\r\n");
            }
            if (pop == -2) {
                fwr1.write(cnt + ") Точка (" + mas[0][i] + ";" + mas[1][i] + ")\tПопала на границу фигуры 2\r\n");
            }
        }
        fwr1.flush();
        fwr1.close();
    }

    public static int FIGURE(int x, int y) {
        if (fig_1(x,y)==1){
            return 1;
        }
        else if (fig_1(x,y)==0) {
            return 0;
        }
        else{
            return -1;
        }
    }

    public static void readFile(String fileName) throws Exception {
        File f = new File(fileName);
        if(!f.exists() && f.isFile()) {
            throw new FileNotFound("File not found");
        }
    }
    public static class FileNotFound extends Exception{
        public FileNotFound(String message){
            super(message);
        }
    }
    public static int fig_1(double x, double y) {
        double v1 = Math.pow(x + 1, 2) + Math.pow(y - 1, 2), v2 = Math.pow(x - 4, 2) + Math.pow(y + 1, 2);
        if  (((x>=-1 & x<=1 & y>=-1 & y<=5 & y<-1.5*x+3.5 & y>3*x-1 & y>-2*x-1)||//1 фигура 1 и 3 треуг
                (y>=-1 & y<=3 & x>=-3 & x<=-1 & y<v1 & y>-v1))){return 1;}//1 фигура 2 полуокр
        else if ((((x>=2 & x<=4 & y>=0 & y<=1 & y>-x+3)||
                (x>2 & x<3 & y<0 & y>-1 & y<x-3)||
                (y>x-7 & x>=4 & x<=6 & y>=-3 & y<=-1)||
                (x>=2 & x<=4 & y>=-3 & y<=-1 & y<-v2)||
                (x>=4 & x<=6 & y>=-1 & y<=1 & y<v2)))){return 2;} //oblast

        else if ((x>=-1 & x<=1 & y>=2 & y<=5 & y==-1.5*x+3.5)||
                (y==3*x-1 & y>=-1 & y<=2 & x>=0 & x<=1)||
                (x>=-1 & x<=0 & y<=1 & y>=-1 & y==-2*x-1)||
                (x==-1 & ((y>=-1 & y<=1)||(y>=3 & y<=5)))||
                (x>=-3 & x<=-1 & y>=-1 & y<=3 & 4==v1)) {return -1;} //phigure

        else if ((x>=2 & x<=4 & y==1)||(x>=2 & x<=4 & y<=-1 & y>=-3 & 4==v2)||
                (x>4 & x<=6 & y<=1 & y>=-1 & 4==v2)||
                (x>=4 & x<=6 & y>=-3 & y<=-1 & y==x-7)||
                (x>=2 & x<=3 & y<=1 & y>=0 & y==-x+3)||
                (x>=2 & x<=3 & y<=0 & y>=-1 & y==x-3)){return -2;}//phigure
        return 0;
    }
}