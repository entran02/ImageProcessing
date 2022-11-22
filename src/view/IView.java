package view;

import java.awt.event.ActionListener;

import model.Image;

public interface IView {

  void makeVisible(ActionListener actionEvent);

  void displayImage(Image image);

  void displayHistogram(Image image);

}
