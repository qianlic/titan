import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UserLogin extends JFrame  implements ActionListener {

    private JPanel outer;
    private JLabel lusername;
    private JLabel lpassword;
    private JLabel lcaptcha;
    private JTextField tusername;
    private JPasswordField tpassword;
    private JTextField tcaptcha;
    private JPanel btn;
    private JButton btnOK;
    private JButton btnExit;

    public UserLogin(byte[] image) {
        initGUI(image);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    private void initGUI(byte[] image) {
        try {
            setTitle("用户登录：");
            getContentPane().setLayout(null);

            outer = new JPanel();
            getContentPane().add(outer);
            outer.setBounds(41, 34, 313, 194);
            outer.setBorder(BorderFactory.createTitledBorder("登录信息:"));
            outer.setLayout(null);

            initContext(image);

            btn = new JPanel();
            btnOK = new JButton("登录");
            btnOK.addActionListener(this);
            btnExit = new JButton("取消");
            btnExit.addActionListener(this);
            btn.add(btnOK);
            btn.add(btnExit);
            getContentPane().add(btn);
            btn.setBounds(41,254,313,370);

            setSize(400, 380);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initContext(byte[] image){
        lusername = new JLabel();
        outer.add(lusername);
        lusername.setText("用户名:");
        lusername.setBounds(44, 56, 69, 15);

        lpassword = new JLabel();
        outer.add(lpassword);
        lpassword.setText("密码:");
        lpassword.setBounds(44, 90, 69, 15);

        lcaptcha = new JLabel(resizeBuff(image,119,25));
        outer.add(lcaptcha);
        lcaptcha.setBounds(44, 122, 119, 25);

        tusername = new JTextField ();
        outer.add(tusername);
        tusername.setText("");
        tusername.setBounds(119, 52, 133, 22);

        tpassword = new JPasswordField();
        outer.add(tpassword);
        tpassword.setText("");
        tpassword.setBounds(119, 86, 133, 22);

        tcaptcha = new JTextField ();
        outer.add(tcaptcha);
        tcaptcha.setText("");
        tcaptcha.setBounds(169, 122, 83, 22);
    }

    public static ImageIcon resizeBuff(byte[] image, int width, int height){
        InputStream in = new ByteArrayInputStream(image);
        try {
            BufferedImage bufImage = ImageIO.read(in);
            AffineTransform transform = AffineTransform.getScaleInstance(
                    (double)width/(double)bufImage.getWidth(),
                    (double)height/(double)bufImage.getHeight());
            AffineTransformOp op = new AffineTransformOp(transform,
                    AffineTransformOp.TYPE_BILINEAR);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(op.filter(bufImage, null),"png",out);
            return new ImageIcon(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("登录")) {
            JOptionPane.showMessageDialog(this, "您的用户名为" + tusername.getText()
                    + "n" + "你的密码为" + String.valueOf(tpassword.getText()));
            SimpleClient.setUsername(tusername.getText());
            SimpleClient.setPassword(tpassword.getText());
            SimpleClient.setCaptcha(tcaptcha.getText());
            SimpleClient.setContinueThread(true);
        } else if(e.getActionCommand().equals("取消")) {
            System.exit(0);
        }
    }

}
