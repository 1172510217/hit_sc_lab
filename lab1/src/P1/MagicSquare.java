package P1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MagicSquare {
    static boolean isLegalMagicSquare(String filename) throws IOException {
        FileReader fr = new FileReader(filename);
        BufferedReader bf = new BufferedReader(fr);
        int row = 0;//To record the number of rows
        while(bf.readLine() != null) {
            row ++;
        }
        bf.close();
        FileReader fr1 = new FileReader(filename);
        BufferedReader bf1 = new BufferedReader(fr1);
        String line = null;
        int [][] a = new int [row][row];//���ڱ���ָ��ļ��õ�������
        int k = 0;
        while((line = bf1.readLine()) != null) {
            String[] temp = line.split("\t");//���ո�ʽ\t�ָ��ļ���ÿһ��
            for(int i = 0;i < temp.length;i ++) {
                int j = temp[i].length();
                while(--j >= 0) {//�ж����޷�����������ڣ������ų�������С��
                    if(!Character.isDigit(temp[i].charAt(j))) {
                        System.out.print("Illegal MagicSquare\t");
                        bf1.close();
                        return false;
                    }
                }
            }
            if(temp.length != row) {//�ж������Ƿ��������
                System.out.print("Illegal MagicSquare\t");
                bf1.close();
                return false;
            }
            //���ַ���ת��Ϊ��ֵ��ͬʱ�ж��Ƿ�Ϊ0
            for(int i = 0;i < temp.length;i ++) {
                if( (a[k][i] = Integer.valueOf(temp[i]) ) == 0) {
                    System.out.print("Illegal MagicSquare\t");
                    bf1.close();
                    return false;
                }
            }
            k++;
        }
        bf1.close();
        int []rows = new int [row];
        int []columns = new int [row];
        int diagonal1 = 0, diagonal2 = 0;
        for(int i = 0;i < row;i ++) {//Ŀ���Ǽ�����֮�ͣ��кͣ��Խ���֮��
            for(int j = 0;j < row;j ++) {
                rows[i] += a[i][j];
                columns[j] += a[i][j];
            }
            diagonal1 += a[i][i];
            diagonal2 += a[i][row-1];
        }
        for(int i = 0;i < row;i ++) {//�ж�ÿ��֮�ͣ�ÿ��֮�ͣ��Խ���֮���Ƿ�Ϊͬһ������
            if(rows[i] != rows[0] || columns[i] != rows[0]) {
                System.out.print("The rows or the columns don't sum to the same constant!\t");
                return false;
            }
        }
        if(diagonal1 != rows[0] || diagonal2 != rows[0]) {//Ϊ�˼򵥣������ó����һ��֮�ͱȽ�
            System.out.print("The diagonals don't sum to the same constant as the rows!\t");
            return false;
        }
        return true;
    }
    static void generateMagicSquare(int n) throws IOException {
        if(n < 0) {//�ж������n�Ƿ�Ϊ����
            System.out.println("False, please input a positive integer!");
            return;
        }
        else if(n % 2 == 0) {//�ж������n�Ƿ�Ϊż��
            System.out.println("False, please input an odd  number!");
            return;
        }
        else {
            int magic[][] = new int[n][n];//���ڱ������ɵĻ÷�����
            int row = 0, col = n / 2, i, j, square = n * n;//�൱�ڽ�n��n����1��ʼ��������Ȼ������n��n���ո���ȥ
            for (i = 1; i <= square; i++) {//����ԭ�������������Ϸ���д�÷�����
                magic[row][col] = i;//��1���ڵ�0������
                if (i % n == 0)//��ԭ������д��λ���Ѿ�����д�����Ϊ�µ�һ��
                    row++;
                else {
                    if (row == 0)//������Ϊ0�����������Ϊn-1
                        row = n - 1;
                    else//�������������
                        row--;
                    if (col == (n - 1))//�����������������Ϊ0
                        col = 0;
                    else//���򣬼���������
                        col++;
                }
            }
            String filename = "src/P1/txt/6.txt";
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    bw.write(magic[i][j] + "\t");//�������Ļ÷�����д�뵽�ļ�6.txt�У���ע���ʽ��\t
                }
                if(i < n-1) {//�ǵ��������
                bw.write("\n");
                }
            }
            bw.close();
        }
    }
    public static void main(String[] args) throws IOException {
        String []filename = new String [6];//���ļ��������ַ��������м��Է���
        filename[0]= "src/P1/txt/1.txt";
        filename[1]= "src/P1/txt/2.txt";
        filename[2]= "src/P1/txt/3.txt";
        filename[3]= "src/P1/txt/4.txt";
        filename[4]= "src/P1/txt/5.txt";
        filename[5]= "src/P1/txt/6.txt";
        for(int i = 0;i < 5;i ++) {//��������жϸ����ļ�
            System.out.println(isLegalMagicSquare(filename[i]));
        }
        
        System.out.print("Please input an odd number to generate a MagicSquare:");
        Scanner inputn = new Scanner(System.in);
        int n = inputn.nextInt();
        generateMagicSquare(n);//��������Ľ���n�����÷�
        inputn.close();
        
        System.out.println(isLegalMagicSquare(filename[5]));//�ж�6.txt�Ƿ�����÷�Ҫ��
    }
}