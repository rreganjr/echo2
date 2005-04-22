/* 
 * This file is part of the Echo Web Application Framework (hereinafter "Echo").
 * Copyright (C) 2002-2005 NextApp, Inc.
 *
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 */

package nextapp.echo2.testapp.interactive.testscreen;

import nextapp.echo2.app.FillImage;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.PasswordField;
import nextapp.echo2.app.Row;
import nextapp.echo2.app.SplitPane;
import nextapp.echo2.app.TextArea;
import nextapp.echo2.app.TextField;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import nextapp.echo2.app.layout.SplitPaneLayoutData;
import nextapp.echo2.testapp.interactive.ButtonRow;
import nextapp.echo2.testapp.interactive.StyleUtil;
import nextapp.echo2.testapp.interactive.Styles;

public class TextComponentTest extends SplitPane {
    
    public TextComponentTest() {
        super(SplitPane.ORIENTATION_HORIZONTAL, new Extent(250, Extent.PX));
        setResizable(true);

        SplitPaneLayoutData splitPaneLayoutData;
        
        ButtonRow controlsRow = new ButtonRow();
        controlsRow.setStyleName(Styles.TEST_CONTROLS_ROW_STYLE_NAME);
        add(controlsRow);

        Row testRow = new Row();
        testRow.setCellSpacing(new Extent(15));
        splitPaneLayoutData = new SplitPaneLayoutData();
        splitPaneLayoutData.setInsets(new Insets(15));
        testRow.setLayoutData(splitPaneLayoutData);
        add(testRow);
        
        final TextField textField = new TextField();
        textField.setBorder(new Border(1, Color.BLUE, Border.STYLE_SOLID));
        testRow.add(textField);
        
        final PasswordField passwordField = new PasswordField();
        passwordField.setBorder(new Border(1, Color.BLUE, Border.STYLE_SOLID));
        testRow.add(passwordField);
        
        final TextArea textArea = new TextArea();
        textArea.setBorder(new Border(1, Color.BLUE, Border.STYLE_SOLID));
        testRow.add(textArea);
        
        controlsRow.addButton("Set Text to Multiple Lines", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = "This\nis\na\ntest.";
                textField.getDocument().setText(text);
                passwordField.getDocument().setText(text);
                textArea.getDocument().setText(text);
            }
        });
        
        controlsRow.addButton("Test HTML Encoding", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = "<b>this should NOT be bold</b>";
                textField.getDocument().setText(text);
                passwordField.getDocument().setText(text);
                textArea.getDocument().setText(text);
            }
        });
        
        controlsRow.addButton("Test Whitespace Encoding", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = "   There   are   three   spaces   leading,   trailing,   "
                        + "and   between   each   word.   ";
                textField.getDocument().setText(text);
                passwordField.getDocument().setText(text);
                textArea.getDocument().setText(text);
            }
        });
        
        controlsRow.addButton("Change Border (All Attributes)", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Border border = StyleUtil.randomBorder();
                textField.setBorder(border);
                passwordField.setBorder(border);
                textArea.setBorder(border);
            }
        });
        controlsRow.addButton("Change Border Color", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Border border = textField.getBorder();
                border = new Border(border.getSize(), StyleUtil.randomColor(), border.getStyle());
                textField.setBorder(border);
                passwordField.setBorder(border);
                textArea.setBorder(border);
            }
        });
        controlsRow.addButton("Change Border Size", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Border border = StyleUtil.nextBorderSize(textField.getBorder());
                textField.setBorder(border);
                passwordField.setBorder(border);
                textArea.setBorder(border);
            }
        });
        controlsRow.addButton("Change Border Style", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Border border = StyleUtil.nextBorderStyle(textField.getBorder());
                textField.setBorder(border);
                passwordField.setBorder(border);
                textArea.setBorder(border);
            }
        });
        controlsRow.addButton("Toggle Background Image", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FillImage backgroundImage = textField.getBackgroundImage();
                if (backgroundImage == null) {
                    textField.setBackgroundImage(Styles.BG_NW_SHADOW);
                    passwordField.setBackgroundImage(Styles.BG_NW_SHADOW);
                    textArea.setBackgroundImage(Styles.BG_NW_SHADOW);
                } else {
                    textField.setBackgroundImage(null);
                    passwordField.setBackgroundImage(null);
                    textArea.setBackgroundImage(null);
                }
            }
        });
        controlsRow.addButton("Set Foreground", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color color = StyleUtil.randomColor();
                textField.setForeground(color);
                passwordField.setForeground(color);
                textArea.setForeground(color);
            }
        });
        controlsRow.addButton("Clear Foreground", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setForeground(null);
                passwordField.setForeground(null);
                textArea.setForeground(null);
            }
        });
        controlsRow.addButton("Set Background", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color color = StyleUtil.randomColor();
                textField.setBackground(color);
                passwordField.setBackground(color);
                textArea.setBackground(color);
            }
        });
        controlsRow.addButton("Clear Background", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setBackground(null);
                passwordField.setBackground(null);
                textArea.setBackground(null);
            }
        });
        controlsRow.addButton("Insets -> null", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setInsets(null);
                passwordField.setInsets(null);
                textArea.setInsets(null);
            }
        });
        controlsRow.addButton("Insets -> 0px", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setInsets(new Insets(0));
                passwordField.setInsets(new Insets(0));
                textArea.setInsets(new Insets(0));
            }
        });
        controlsRow.addButton("Insets -> 5px", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setInsets(new Insets(5));
                passwordField.setInsets(new Insets(5));
                textArea.setInsets(new Insets(5));
            }
        });
        controlsRow.addButton("Insets -> 10/20/30/40px", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setInsets(new Insets(10, 20, 30, 40));
                passwordField.setInsets(new Insets(10, 20, 30, 40));
                textArea.setInsets(new Insets(10, 20, 30, 40));
            }
        });
        controlsRow.addButton("Width -> null", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setWidth(null);
                passwordField.setWidth(null);
                textArea.setWidth(null);
            }
        });
        controlsRow.addButton("Width -> 500px", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setWidth(new Extent(500, Extent.PX));
                passwordField.setWidth(new Extent(500, Extent.PX));
                textArea.setWidth(new Extent(500, Extent.PX));
            }
        });
        controlsRow.addButton("Width -> 100%", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setWidth(new Extent(100, Extent.PERCENT));
                passwordField.setWidth(new Extent(100, Extent.PERCENT));
                textArea.setWidth(new Extent(100, Extent.PERCENT));
            }
        });
        controlsRow.addButton("Height -> null", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setHeight(null);
                passwordField.setHeight(null);
                textArea.setHeight(null);
            }
        });
        controlsRow.addButton("Height -> 300px", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setHeight(new Extent(300, Extent.PX));
                passwordField.setHeight(new Extent(300, Extent.PX));
                textArea.setHeight(new Extent(300, Extent.PX));
            }
        });
    }
}
