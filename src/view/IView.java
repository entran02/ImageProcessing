package view;

import java.awt.event.ActionListener;

import javax.swing.event.ChangeListener;

import model.Image;

public interface IView {

  void makeVisible(ActionListener actionEvent, ChangeListener changeEvent);

  void displayImage(Image image);

  void displayHistogram(Image image);

}
