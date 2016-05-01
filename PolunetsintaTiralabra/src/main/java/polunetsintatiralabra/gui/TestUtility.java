package polunetsintatiralabra.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import polunetsintatiralabra.algorithms.Astar;
import polunetsintatiralabra.algorithms.JumpPointSearch;

public class TestUtility extends JFrame implements ActionListener {

    public TestUtility() {
        super("TestUtility");
        this.setLayout(new FlowLayout());
        JButton asAvg = new JButton();
        JButton jpsAvg = new JButton();
        asAvg.setActionCommand("Average A*");
        asAvg.setText("A*");
        asAvg.addActionListener(this);
        jpsAvg.setActionCommand("Average JPS");
        jpsAvg.setText("JPS");
        jpsAvg.addActionListener(this);
        this.add(asAvg);
        this.add(jpsAvg);
        this.setSize(200, 200);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double total_time = 0;
        if ("Average A*".equals(e.getActionCommand())) {
            for (int i = 0; i < 99999; i++) {
                Grid.flush();
                Astar as = new Astar();
                double t = System.nanoTime();
                as.run(Grid.getStart(), Grid.getEnd());
                t = (System.nanoTime() - t) / 100000;
                total_time += t;
                if (i % 10000 == 0) {
                    System.out.println(i / 1000 + "%");
                }
            }
            System.out.println(total_time / 100000);

        }
        if ("Average JPS".equals(e.getActionCommand())) {
            for (int i = 0; i < 99999; i++) {
                Grid.flush();
                JumpPointSearch as = new JumpPointSearch();
                double t = System.nanoTime();
                as.search(Grid.getStart(), Grid.getEnd());
                t = (System.nanoTime() - t) / 100000;
                total_time += t;
                if (i % 10000 == 0) {
                    System.out.println(i / 1000 + "%");
                }
            }
            System.out.println(total_time / 100000);

        }
    }
}
