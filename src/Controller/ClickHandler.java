package Controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ClickHandler implements EventHandler<MouseEvent>{

    @Override
    public void handle(MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY) {leftClick();}
        else if(event.getButton() == MouseButton.SECONDARY) {rightClick();}

        //TODO check bounds, otherwise find where click and apply left or right click action
        //TODO consider using shift or ctrl clicks for something
        //TODO add click and drag event handler
    }

    private void leftClick() {
        System.out.println("You left clicked!");
    }

    private void rightClick() {
        System.out.println("You right clicked!");
    }
}
