package bibibimd;
import java.util.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class beo extends JFrame{
   static String[] fruit = {"imges/1 (Custom).png","imges/2 (Custom).png",
			"imges/3 (Custom).png","imges/4 (Custom).png","imges/5 (Custom).png",
			"imges/6 (Custom).png","imges/7 (Custom).png","imges/8 (Custom).png",
			"imges/9 (Custom).png","imges/10 (Custom).png","imges/1 (Custom).png",
			"imges/2 (Custom).png","imges/3 (Custom).png","imges/4 (Custom).png",
			"imges/5 (Custom).png","imges/6 (Custom).png","imges/7 (Custom).png",
			"imges/8 (Custom).png","imges/9 (Custom).png","imges/10 (Custom).png"}; // 과일 이미지
   private JButton btn[] = new JButton[20];
   
   static int openCount = 0; // 오픈된 카드 갯수
   static int buttonIndexSave1 = 0; // 첫번째 카드
   static int buttonIndexSave2 = 0; // 두번째 카드
   static Timer timer; // 타이머
   static int successCount = 0; // 성공 횟수
   
   ImageIcon qq = new ImageIcon("imges/qq.jpg"); // 물음표 이미지
   Image img = qq.getImage();
   Image changeImg =img.getScaledInstance(200,180,Image.SCALE_SMOOTH);
   ImageIcon changeIcon = new ImageIcon(changeImg);
   
   public beo()
   {
      setTitle("빙고 게임");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Container c = getContentPane();
      c.setLayout(new GridLayout(4,5,10,10));
      
      mixCard();
      
      for(int i =0; i<btn.length; i++)
      {
         btn[i] = new JButton();
         btn[i].setIcon(changeIcon);
         c.add(btn[i]);
         btn[i].addActionListener(new MyActionListener());
      }
      
      setSize(800,800);
      setVisible(true);
   }
   static ImageIcon changeImage(String filename) // 과일 이미지
   {
	      ImageIcon icon = new ImageIcon("./" + filename);
	      Image originImage = icon.getImage();
	      Image changedImage = originImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
	      ImageIcon icon_new = new ImageIcon(changedImage);
	      
	      return icon_new;
	}

   static void mixCard()
   {
      Random rand = new Random();
      for(int i = 0; i<1000; i++) 
      {
         int random = rand.nextInt(19) + 1;
         String temp = fruit[0];
         fruit[0] =  fruit[random];
         fruit[random] = temp;
      }
   }
   class MyActionListener implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent e) {
         
         if(openCount == 2) 
         {
            return;
         }
         // TODO Auto-generated method stub
            JButton b = (JButton)e.getSource();
            int num = getButtonIndex(b);            
            b.setIcon(changeImage(fruit[num]));
            
            openCount++;
            
            if(openCount == 1) 
            {
               buttonIndexSave1 = num;
            }
            else if(openCount == 2) 
            {
               buttonIndexSave2 = num;
               
               boolean isBingo = checkCard(buttonIndexSave1, buttonIndexSave2);
               if(isBingo == true) 
               {
                  openCount = 0;
                  successCount++;
                  if(successCount == 10) 
                  {
                     int result = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?","Game RESET",JOptionPane.YES_NO_OPTION);
                     if(result==JOptionPane.YES_OPTION)
                     {
                    	 System.exit(0);
                     }
                     else
                     {
                    	 for(int i =0; i<btn.length; i++)
                    	 {
                    		 btn[i].setIcon(changeIcon);
                    	 }
                    	 mixCard();
                     }
                  }
               }
               else 
               {
                  timetime();
               }
            }
      }
      public void timetime()
      {
         timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                  openCount = 0;
                  btn[buttonIndexSave1].setIcon(changeIcon);
                  btn[buttonIndexSave2].setIcon(changeIcon);
                  timer.stop();
            }
         });
         timer.start();
      }
      public boolean checkCard(int index1, int index2)
      {
         if(index1 == index2) 
         {
            return false;
         }
         if(fruit[index1].equals(fruit[index2])) 
         {
            return true;
         }
         else 
         {
            return false;
         }
      }

      public int getButtonIndex(JButton button)
      {
         int num = 0;
         for(int i =0; i<20; i++)
         {
            if(btn[i] == button)
            {
               num = i;
            }
         }
         return num;
      }
   }
   public static void main(String[]args)
   {
      new beo();
   }
}