/**
 * 
 * COPYRIGHT (C) 2010, 2011, 2012, 2013, 2014 AGETO Innovation GmbH
 * 
 * Authors Christian Kahlo, Ralf Wondratschek
 * 
 * All Rights Reserved.
 * 
 * Contact: PersoApp, http://www.persoapp.de
 * 
 * @version 1.0, 30.07.2013 13:50:47
 * 
 *          This file is part of PersoApp.
 * 
 *          PersoApp is free software: you can redistribute it and/or modify it
 *          under the terms of the GNU Lesser General Public License as
 *          published by the Free Software Foundation, either version 3 of the
 *          License, or (at your option) any later version.
 * 
 *          PersoApp is distributed in the hope that it will be useful, but
 *          WITHOUT ANY WARRANTY; without even the implied warranty of
 *          MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *          Lesser General Public License for more details.
 * 
 *          You should have received a copy of the GNU Lesser General Public
 *          License along with PersoApp. If not, see
 *          <http://www.gnu.org/licenses/>.
 * 
 *          Diese Datei ist Teil von PersoApp.
 * 
 *          PersoApp ist Freie Software: Sie können es unter den Bedingungen der
 *          GNU Lesser General Public License, wie von der Free Software
 *          Foundation, Version 3 der Lizenz oder (nach Ihrer Option) jeder
 *          späteren veröffentlichten Version, weiterverbreiten und/oder
 *          modifizieren.
 * 
 *          PersoApp wird in der Hoffnung, dass es nützlich sein wird, aber OHNE
 *          JEDE GEWÄHRLEISTUNG, bereitgestellt; sogar ohne die implizite
 *          Gewährleistung der MARKTFÄHIGKEIT oder EIGNUNG FÜR EINEN BESTIMMTEN
 *          ZWECK. Siehe die GNU Lesser General Public License für weitere
 *          Details.
 * 
 *          Sie sollten eine Kopie der GNU Lesser General Public License
 *          zusammen mit diesem Programm erhalten haben. Wenn nicht, siehe
 *          <http://www.gnu.org/licenses/>.
 * 
 */
package de.persoapp.desktop.gui.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.persoapp.core.client.IEAC_Info;
import de.persoapp.core.client.PropertyResolver;
import de.persoapp.core.client.IMainView.EventListener;
import de.persoapp.desktop.Configuration;
import de.persoapp.desktop.MainView;
import de.persoapp.desktop.gui.MyMouseListener;
import de.persoapp.desktop.gui.panel.ButtonPanel;
import de.persoapp.desktop.gui.panel.CAPanel;
import de.persoapp.desktop.gui.panel.DataPanel;
import de.persoapp.desktop.gui.panel.HelpPanel;
import de.persoapp.desktop.gui.panel.KeypadPanel;
import de.persoapp.desktop.gui.panel.PinPanel;
import de.persoapp.desktop.gui.panel.ServiceProviderPanel;
import de.persoapp.desktop.gui.panel.StatusBarPanel;

/**
 * <p>
 * The MainFrame displays the necessary informations and provides the
 * GUI to handle user interactions properly, to maintain the communication
 * between the user and an eID service provider.
 * </p>
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public class MainFrame extends JFrame implements HelpPanelProvider, SidebarProvider {

	private static final long				serialVersionUID	= -7125602795219536236L;

	/**
	 * The panel for displaying the informations about the used service
	 * provider.
	 */
	private ServiceProviderPanel			serviceProviderPanel;
	
	/**
	 * The panel for displaying the informations about the used certificate
	 * authority.
	 */
	private CAPanel							caPanel;
	
	/**
	 * The panel for marking the personal data, which is needed for the
	 * <em>eID-process</em>.
	 */
	private DataPanel						dataPanel;
	
	/**
	 * The panel for inserting the necessary pin to authenticate the user.
	 */
	private PinPanel						pinPanel;
	
	/**
	 * The panel which holds the Confirm- and Cancel-Button.
	 */
	private ButtonPanel						buttonPanel;
	
	/**
	 * The status bar which includes a progress bar.
	 */
	private StatusBarPanel					statusBarPanel;
	
	/**
	 * The panel which holds the {@link PinPanel}.
	 */
	private KeypadPanel						keypadPanel;
	
	/**
	 * The panel, which holds informations about the
	 * PersoApp-Application.
	 */
	private HelpPanel						helpPanel;
	
	/**
	 * The main panel, which holds all components of the {@link MainFrame}.
	 */
	private JPanel							mainPanel;
	
	/**
	 * The displayed picture of the PersoApp-Application.
	 */
	private JLabel							pic;

	/**
	 * Localized message bundle for user interaction.
	 */
	private final PropertyResolver.Bundle	textBundle;

	/**
	 * The flag for enabling or disabling the currently used {@link PinPanel} 
	 */
	private boolean							pinEnabled;
	
	/**
	 * The flag for showing the sidebar.
	 */
	private boolean							showSideBar;

	/**
	 * Creates a new instance of the {@link MainFrame}.
	 */
	public MainFrame() {
		super();
		textBundle = PropertyResolver.getBundle("text");

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(Configuration.RESIZABLE);
		this.setTitle(textBundle.get("window_title"));
		this.setIconImage(Configuration.WINDOW_ICON);
		this.setName("MainFrame");

		this.pinEnabled = true;
		this.showSideBar = false;

		initPanels();
		drawPanels();
		addListener();
	}

	/**
	 * Initializes the panels of the {@link MainFrame}.
	 */
	private void initPanels() {
		mainPanel = new JPanel(true);
		mainPanel.setLayout(new GridBagLayout());

		pic = new JLabel(new ImageIcon(Configuration.LOGO));
		dataPanel = new DataPanel();
		buttonPanel = new ButtonPanel();
		pinPanel = new PinPanel(buttonPanel, textBundle.get("npa_pin"), 6);
		serviceProviderPanel = new ServiceProviderPanel();
		caPanel = new CAPanel();
		helpPanel = new HelpPanel();
		keypadPanel = new KeypadPanel(pinPanel);

		statusBarPanel = new StatusBarPanel();
		if (Configuration.CLAIM_TEXT != null) {
			statusBarPanel.setProgressBarValue(Configuration.CLAIM_TEXT, false);
		} else {
			statusBarPanel.setProgressBarValue(textBundle.get("MainFrame_StatusbarPanel_init"), 0, false);
		}
	}

	/**
	 * Draws the panels of the {@link MainFrame}.
	 */
	private void drawPanels() {
		final GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.BOTH;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.gridx = 0;
		cons.gridy = 0;
		cons.insets = new Insets(15, 5, 5, 5);
		cons.weightx = 1;
		cons.weighty = 0;

		mainPanel.add(pic, cons);

		cons.insets = new Insets(5, 5, 5, 5);
		cons.gridy = 1;
		cons.weighty = 1;
		mainPanel.add(serviceProviderPanel, cons);

		cons.gridx = 1;
		cons.gridy = 0;
		cons.gridheight = 2;
		cons.weightx = 0;
		mainPanel.add(dataPanel, cons);

		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridheight = 2;
		cons.weightx = 1;
		cons.weighty = 0;
		mainPanel.add(caPanel, cons);

		cons.gridx = 1;
		cons.gridheight = 1;
		cons.weightx = 0;
		mainPanel.add(pinPanel, cons);

		cons.gridy = 3;
		mainPanel.add(buttonPanel, cons);

		cons.gridx = 0;
		cons.gridy = 4;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.gridwidth = 2;
		mainPanel.add(statusBarPanel, cons);

		this.getContentPane().add(mainPanel, BorderLayout.CENTER);
	}

	/**
	 * Adds different kinds of listeners to the {@link MainFrame} and their
	 * components to provide the main functionality.
	 */
	private void addListener() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				returnResultAbort();
			}
		});

		MyMouseListener.addListener(this, caPanel, "CAPanel");
		MyMouseListener.addListener(this, dataPanel, "DataPanel");
		MyMouseListener.addListener(this, pinPanel, "PinPanel");
		MyMouseListener.addListener(this, serviceProviderPanel, "ServiceProviderPanel");
		MyMouseListener.addListener(this, buttonPanel, "ButtonPanel");
		MyMouseListener.addListener(this, helpPanel, "HelpPanel");
		MyMouseListener.addListener(this, keypadPanel, "KeypadPanel");

		buttonPanel.getCancel().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				returnResultAbort();
			}
		});

		buttonPanel.getConfirm().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				returnResultConfirm();
			}
		});

		buttonPanel.getConfirm().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(final KeyEvent e) {
				if (e.getKeyChar() == '\n') {
					returnResultConfirm();
				}
			}
		});
	}

	/**
	 * Shows the progress of the current operation according to the provided arguments.
	 * 
	 * @param message
	 *            - The message to display.
	 * @param amount
	 *            - The amount to fill the progress bar.
	 * @param enabled
	 *            - Enables or disables the progress bar.
	 */
	public void showProgress(final String message, final int amount, final boolean enabled) {
		statusBarPanel.setProgressBarValue(message, amount, enabled);
	}

	/**
	 * Initializes the different panels according to the informations from the
	 * {@link IEAC_Info}.
	 * 
	 * @param eacInfo
	 *            - The informations about the certificate.
	 */
	public void init(final IEAC_Info eacInfo) {
		pinPanel.clear();
		buttonPanel.getCancel().setEnabled(true);

		serviceProviderPanel.fillCertificate(eacInfo);
		caPanel.fillCertificate(eacInfo);

		dataPanel.fillCertificate(eacInfo);

		// TODO: remove logic layer dependency 
		final int attempts = (Integer) MainView.getInstance().triggerEvent(EventListener.EVENT_TEST_PIN_STATE);
		helpPanel.setPINState(attempts);

		this.pack();
		this.setLocationRelativeTo(null);
	}

	/**
	 * Returns the result of the <em>Card Holder Authorization Template</em>.
	 * 
	 * @return Returns the result of the <em>Card Holder Authorization Template</em>.
	 */
	public long getResultChat() {
		return dataPanel.getResultChat();
	}

	/**
	 * Fetches the final result, after pressing the <em>confirm</em> button. The
	 * final result includes the result of the <em>CHAT</em> and the pin code.
	 */
	public void returnResultConfirm() {
		((MainView) MainView.getInstance()).setResult(dataPanel.getResultChat(), pinPanel.getPinCode(0), true);

		buttonPanel.getConfirm().setEnabled(false);
		buttonPanel.getCancel().setEnabled(false);
	}

	/**
	 * Quits the processing of informations.
	 */
	public void returnResultAbort() {
		this.setVisible(false);

		// TODO: remove logic layer dependency 
		((MainView) MainView.getInstance()).setResult(-1L, null, false);
	}

	@Override
	public void pack() {
		int width = serviceProviderPanel.getPreferredSize().width;
		int height = dataPanel.getPreferredSize().height - pic.getPreferredSize().height - 15;
		serviceProviderPanel.setPreferredSize(new Dimension(width, height));

		width = caPanel.getPreferredSize().width;
		height = pinPanel.getPreferredSize().height + buttonPanel.getPreferredSize().height + 15;
		caPanel.setMinimumSize(new Dimension(width, height));
		caPanel.setPreferredSize(new Dimension(width, height));

		width = helpPanel.getPreferredSize().width;
		height = caPanel.getPreferredSize().height + statusBarPanel.getPreferredSize().height + 10;
		keypadPanel.setMinimumSize(new Dimension(width, height));

		super.pack();

		this.setMinimumSize(this.getPreferredSize());
	}

	@Override
	public void addSidebar() {
		showSideBar = true;

		final GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = 1;
		cons.gridx = 2;
		cons.gridy = 0;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.weightx = 0;
		cons.weighty = 1;

		if (pinEnabled) {

			cons.gridheight = 2;
			mainPanel.add(helpPanel, cons);

			cons.gridheight = 3;
			cons.gridy = 2;
			cons.weighty = 0;
			mainPanel.add(keypadPanel, cons);

		} else {

			cons.gridheight = 5;
			mainPanel.add(helpPanel, cons);
		}

		this.setSize(new Dimension(this.getWidth() + helpPanel.getPreferredSize().width + 10, this.getHeight()));
		this.setMinimumSize(new Dimension(this.getMinimumSize().width + helpPanel.getPreferredSize().width + 10, this
				.getMinimumSize().height));
	}

	@Override
	public void removeSidebar() {
		showSideBar = false;

		mainPanel.remove(keypadPanel);
		mainPanel.remove(helpPanel);

		this.setMinimumSize(new Dimension(this.getMinimumSize().width - helpPanel.getSize().width - 10, this
				.getMinimumSize().height));
		this.setSize(this.getWidth() - helpPanel.getSize().width - 10, this.getHeight());
	}

	/**
	 * Enables the KeypadPanel, if the sidebar is currently shown.
	 * 
	 * @param enabled
	 *            - If set to <b>true</b>, the KeypadPanel is enabled,
	 *            otherwise <b>false</b>.
	 */
	public void setKeypadPanelEnabled(final boolean enabled) {
		if (showSideBar) {
			keypadPanel.setEnabled(enabled);
		}
	}

	@Override
	public void setHelpPanelText(final String header, final String description) {
		if (showSideBar) {
			helpPanel.setText(header, description);
		}
	}

	@Override
	public void clearHelpPanelText() {
		// text deletion is not needed. 
		// if the mouse pointer is out of the window scope the text will be removed.
	}
	
	/**
	 * <p>
	 * This method enables the possibility to use the {@link PinPanel} and the
	 * virtual keypad or the connected keyboard, if the argument
	 * <em>enabled</em> is set to <strong>true</strong>. Otherwise the
	 * PinRows are removed and the user is forced to use his card
	 * reader for the insertion of the required numbers.
	 * </p>
	 * 
	 * @param enabled
	 *            - Set to <strong>true</strong>, to use the integrated
	 *            PinRows and the virtual keypad or the connected
	 *            keyboard. Set to <strong>false</strong> to use the connected
	 *            card reader.
	 */
	public void setPinEnabled(final boolean enabled) {
		this.pinEnabled = enabled;

		pinPanel.setPinEnabled(enabled);

		if (Configuration.CLAIM_TEXT != null) {
			statusBarPanel.setProgressBarValue(Configuration.CLAIM_TEXT, 0, false);
		} else {
			if (enabled) {
				statusBarPanel.setProgressBarValue(textBundle.get("MainFrame_StatusbarPanel_init"), 0, false);
			} else {
				statusBarPanel.setProgressBarValue("", 0, false);
			}
		}

		if (showSideBar) {
			keypadPanel.shuffle();

			if (keypadPanel.getParent() == mainPanel && !enabled || keypadPanel.getParent() != mainPanel && enabled) {

				// add keypad panel if necessary (activate pin in gui) or remove
				this.removeSidebar();
				this.addSidebar();
			}
		}
	}
}
