import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    static int seconds=0;
    static JLabel timerLabel;
    static DecimalFormat decimalFormat = new DecimalFormat("00");
    public static void main(String[] args) {

        final int[] sayi = {rastgele_sayi()};

        JFrame jFrame=new JFrame("Number guessing");
        jFrame.setBounds(800,300,350,450);

        jFrame.getContentPane().setBackground(Color.CYAN);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timerLabel=new JLabel("Süre: 00:00",JLabel.CENTER);
        timerLabel.setFont(new Font("Serif",Font.BOLD,20));
        timerLabel.setBounds(95,5,150,50);
        jFrame.getContentPane().add(timerLabel,BorderLayout.CENTER);

        //süre
        Timer timer=new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                seconds++;
                updateTimerLabel();
            }
        };
        timer.scheduleAtFixedRate(task,1000,1000);

        //tahmin girme
        JTextField textField=new JTextField();
        textField.setBounds(120,80,100,30);
        jFrame.getContentPane().add(textField);

        //tahmin edilene göre cevap
        JLabel label=new JLabel();
        label.setBounds(110,200,300,50);
        jFrame.getContentPane().add(label);

        //puan hesaplama (1. oyuncu)
        JLabel label3=new JLabel("oyuncu: ");
        label3.setBounds(50,120,50,50);
        jFrame.getContentPane().add(label3);
        JLabel label1=new JLabel("0");
        label1.setBounds(120,120,50,50);
        jFrame.getContentPane().add(label1);

        //puan hesaplama (pc)
        JLabel label4=new JLabel("pc: ");
        label4.setBounds(230,120,50,50);
        jFrame.getContentPane().add(label4);
        JLabel label2=new JLabel("0");
        label2.setBounds(270,120,50,50);
        jFrame.getContentPane().add(label2);

        final int[] oyuncu = {0};
        final int[] pc = {0};
        final int[] sayac3 = {0};
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tahminEdilen= Integer.parseInt(textField.getText());
                if(sayi[0] ==tahminEdilen && sayac3[0]<10){
                    String time=timer();
                    JOptionPane.showMessageDialog(jFrame,"Tahmin doğru kazandınız!!\nTahmin süresi: "+time+"\nHamle sayısı: "+(sayac3[0]+1));
                    seconds=0;
                    oyuncu[0]++;
                    sayac3[0]=0;
                    label1.setText(String.valueOf(oyuncu[0]));
                    sayi[0] =rastgele_sayi();
                }
                else if(sayi[0] >tahminEdilen && sayac3[0]<10){
                    label.setText("tahmininizden yüksek");
                }
                else if(sayi[0] <tahminEdilen && sayac3[0]<10) {
                    label.setText("tahmininizden düşük");
                }
                else{
                    String time=timer();
                    JOptionPane.showMessageDialog(jFrame,"Tahmin yanlış kaybettiniz:(");
                    seconds=0;
                    pc[0]++;
                    sayac3[0]=0;
                    label2.setText(String.valueOf(pc[0]));
                    sayi[0]=rastgele_sayi();
                }
                sayac3[0]++;
            }
        });




        jFrame.setLayout(null);
        jFrame.setVisible(true);
    }

    static void updateTimerLabel(){
        SwingUtilities.invokeLater(()->{
            String time=timer();
            timerLabel.setText("Süre: " + time);

        });
    }
    static String timer(){
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        String formattedTime = decimalFormat.format(minutes) + ":" + decimalFormat.format(remainingSeconds);
        return formattedTime;
    }
    static int rastgele_sayi(){
        Random random =new Random();
        int sayi=random.nextInt(100)+1;
        System.out.println(sayi);
        return sayi;
    }
}
