package view;

import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.GameController;

public class UndoOptionPane extends JPanel {

	private JComboBox<Object> cmbUndoList;
	private JLabel lblMessage;
	private String[] numberList;

	@SuppressWarnings("unchecked")
	public UndoOptionPane(AppFrame appFrame, GameController gameController) {

		setLayout(new GridLayout(2, 1));
		setBounds(100, 100, 250, 200);
		if (gameController.getGameEngine().getTurnNo() >= 2) {
			numberList = new String[gameController.getGameEngine().getTurnNo() / 2];
			numberList = getNumberList(gameController.getGameEngine().getTurnNo());
			cmbUndoList = new JComboBox<Object>(numberList);
			cmbUndoList.setSelectedIndex(0);

			lblMessage = new JLabel();
			lblMessage.setText("Current Turn: " + Integer.toString(gameController.getGameEngine().getTurnNo())
					+ ", Please select how many rounds to undo:");

			add(lblMessage);
			add(cmbUndoList);
		}

	}

	public String[] getNumberList(int turnCount) {
		if (turnCount <= 6) {
			numberList = new String[turnCount / 2];
		} else {
			numberList = new String[3];
		}


		if (turnCount / 2 <= 1) {
			numberList[0] = "1";
		} else if (turnCount / 2 <= 2) {
			numberList[0] = "1";
			numberList[1] = "2";
		} else {
			numberList[0] = "1";
			numberList[1] = "2";
			numberList[2] = "3";
		}
		return numberList;
	}

	public int getNumberOfUndos() {
		return cmbUndoList.getSelectedIndex() + 1;
	}
}
