/*
 * Copyright (C) 2008-12  Bernhard Hobiger
 *
 * This file is part of HoDoKu.
 *
 * HoDoKu is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * HoDoKu is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with HoDoKu. If not, see <http://www.gnu.org/licenses/>.
 */
package sudoku;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author hobiwan
 */
public class HistoryDialog extends javax.swing.JDialog {
	private static final long serialVersionUID = 1L;
	private MainFrame mainFrame;
	private String[][] data;
	private String[] puzzles;
	private int[] stepLevels;
	private String[] columnNames = new String[] {
			ResourceBundle.getBundle("intl/HistoryDialog").getString("HistoryDialog.col1"),
			ResourceBundle.getBundle("intl/HistoryDialog").getString("HistoryDialog.col2"),
			ResourceBundle.getBundle("intl/HistoryDialog").getString("HistoryDialog.col3"),
			ResourceBundle.getBundle("intl/HistoryDialog").getString("HistoryDialog.col4") };
	private boolean okPressed = false;
	private boolean doubleClicked = false;

	/**
	 * Creates new form HistoryDialog
	 * 
	 * @param parent
	 * @param modal
	 */
	public HistoryDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();

		mainFrame = (MainFrame) parent;

		initTable();

		previewCheckBox.setSelected(Options.getInstance().isHistoryPreview());

		getRootPane().setDefaultButton(okButton);

		KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
		Action escapeAction = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		};
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
		getRootPane().getActionMap().put("ESCAPE", escapeAction);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */

	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		historyTable = new javax.swing.JTable();
		okButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();
		previewCheckBox = new javax.swing.JCheckBox();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("intl/HistoryDialog"); // NOI18N
		setTitle(bundle.getString("title")); // NOI18N

		historyTable
				.setModel(new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		historyTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		historyTable.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				historyTableMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(historyTable);
		historyTable.getColumnModel().getColumn(0)
				.setHeaderValue(bundle.getString("HistoryDialog.historyTable.columnModel.title0")); // NOI18N
		historyTable.getColumnModel().getColumn(1)
				.setHeaderValue(bundle.getString("HistoryDialog.historyTable.columnModel.title1")); // NOI18N
		historyTable.getColumnModel().getColumn(2)
				.setHeaderValue(bundle.getString("HistoryDialog.historyTable.columnModel.title2")); // NOI18N
		historyTable.getColumnModel().getColumn(3)
				.setHeaderValue(bundle.getString("HistoryDialog.historyTable.columnModel.title3")); // NOI18N

		okButton.setMnemonic(java.util.ResourceBundle.getBundle("intl/HistoryDialog")
				.getString("HistoryDialog.okButton.mnemonic").charAt(0));
		okButton.setText(bundle.getString("HistoryDialog.okButton.text")); // NOI18N
		okButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				okButtonActionPerformed(evt);
			}
		});

		cancelButton.setMnemonic(java.util.ResourceBundle.getBundle("intl/HistoryDialog")
				.getString("HistoryDialog.cancelButton.mnemonic").charAt(0));
		cancelButton.setText(bundle.getString("HistoryDialog.cancelButton.text")); // NOI18N
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});

		previewCheckBox.setMnemonic(java.util.ResourceBundle.getBundle("intl/HistoryDialog")
				.getString("HistoryDialog.previewCheckBox.mnemonic").charAt(0));
		previewCheckBox.setText(bundle.getString("HistoryDialog.previewCheckBox.text")); // NOI18N
		previewCheckBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				previewCheckBoxActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(previewCheckBox)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										layout.createSequentialGroup().addComponent(okButton)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(cancelButton)))
						.addContainerGap()));

		layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] { cancelButton, okButton });

		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(previewCheckBox)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(okButton).addComponent(cancelButton))
						.addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_okButtonActionPerformed
		if (historyTable.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this,
					ResourceBundle.getBundle("intl/HistoryDialog").getString("HistoryDialog.error.message"),
					ResourceBundle.getBundle("intl/HistoryDialog").getString("HistoryDialog.error.title"),
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		okPressed = true;
		setVisible(false);
	}// GEN-LAST:event_okButtonActionPerformed

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancelButtonActionPerformed
		okPressed = false;
		setVisible(false);
	}// GEN-LAST:event_cancelButtonActionPerformed

	private void historyTableMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_historyTableMouseClicked
		if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2) {
			doubleClicked = true;
			okButtonActionPerformed(null);
		}
	}// GEN-LAST:event_historyTableMouseClicked

	private void previewCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_previewCheckBoxActionPerformed
		// System.out.println("preview " + previewCheckBox.isSelected());
		Options.getInstance().setHistoryPreview(previewCheckBox.isSelected());
	}// GEN-LAST:event_previewCheckBoxActionPerformed

	/**
	 * Creates an uneditable DefaultTableModel and fills it with data from
	 * {@link Options}. {@link #puzzles} is filled with the corresponding puzzles as
	 * 81 character strings.
	 */
	private void initTable() {
		List<String> list = Options.getInstance().getHistoryOfCreatedPuzzles();
		data = new String[list.size()][4];
		puzzles = new String[list.size()];
		stepLevels = new int[list.size()];
		DifficultyLevel[] levels = Options.getInstance().getDifficultyLevels();
		DateFormat df = DateFormat.getDateInstance();
		DateFormat tf = DateFormat.getTimeInstance();
		for (int i = 0; i < list.size(); i++) {
			String[] parts = list.get(i).split("#");
			puzzles[i] = parts[0];
			stepLevels[i] = Integer.parseInt(parts[1]);
			Date date = new Date(Long.parseLong(parts[3]));
			data[i][0] = df.format(date);
			data[i][1] = tf.format(date);
			data[i][2] = levels[stepLevels[i]].getName();
			data[i][3] = parts[2];
		}
		historyTable.setModel(new DefaultTableModel(data, columnNames) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		});
		historyTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				tableSelectionChanged(e);
			}
		});
		// set first column wider than the others
		TableCellRenderer renderer = new MyTableCellRenderer();
		TableColumnModel cm = historyTable.getColumnModel();
		for (int i = 0; i < cm.getColumnCount(); i++) {
			TableColumn column = cm.getColumn(i);
			if (i == 0 || i == 1) {
				// first two columns are bigger
				column.setPreferredWidth(100);
				// column.
			} else {
				column.setPreferredWidth(50);
			}
			column.setCellRenderer(renderer);
		}
		// column headers centered
		renderer = historyTable.getTableHeader().getDefaultRenderer();
		JLabel label = (JLabel) renderer;
		label.setHorizontalAlignment(JLabel.CENTER);
	}

	/**
	 * Selection listener for {@link #historyTable}. Sets the puzzle in
	 * {@link MainFrame}.
	 * 
	 * @param e
	 */
	private void tableSelectionChanged(ListSelectionEvent e) {
		// System.out.println(e.getFirstIndex() + "/" + e.getLastIndex() + "/" +
		// e.getValueIsAdjusting() + "/" + historyTable.getSelectedRow());
		if (previewCheckBox.isSelected() && e.getValueIsAdjusting() == false) {
			// row is finally selected
			mainFrame.setPuzzle(puzzles[historyTable.getSelectedRow()]);
		}
	}

	/**
	 * Returns the selected puzzle or null if cancel was pressed. If no puzzle was
	 * selected pressing Ok must not be possible or this method will throw an
	 * exception.
	 *
	 * @return selected puzzle
	 */
	public String getSelectedPuzzle() {
		if (okPressed) {
			return puzzles[historyTable.getSelectedRow()];
		} else {
			return null;
		}
	}

	/**
	 * @return the doubleClicked
	 */
	public boolean isDoubleClicked() {
		return doubleClicked;
	}

	/**
	 * @param args the command line arguments
	 */
	/*
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				HistoryDialog dialog = new HistoryDialog(new javax.swing.JFrame(), true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}*/

	class MyTableCellRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			MyTableCellRenderer comp = (MyTableCellRenderer) super.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, column);
			if (!isSelected) {
				comp.setBackground(Options.getInstance().getDifficultyLevels()[stepLevels[row]].getBackgroundColor());
				comp.setForeground(Options.getInstance().getDifficultyLevels()[stepLevels[row]].getForegroundColor());
			}
			if (column == 3) {
				comp.setHorizontalAlignment(SwingConstants.RIGHT);
			} else {
				comp.setHorizontalAlignment(SwingConstants.CENTER);
			}
			return comp;
		}
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton cancelButton;
	private javax.swing.JTable historyTable;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JButton okButton;
	private javax.swing.JCheckBox previewCheckBox;
	// End of variables declaration//GEN-END:variables

}
