package botTelegram;

import application.Callback;
import application.MenuButton;
import application.SimpleMenu;
import application.VerticalMenu;

public class VerticalMenu extends SimpleMenu {

    /**
     * Aggiunge un bottone al menu nella colonna verticale
     *
     * @param buttonText la scritta presente sul bottone
     * @param action     azione da eseguire quando l'user clicca il bottone
     */
    public VerticalMenu addButton(String buttonText, Callback action) {
        super.addButton(buttonText, action);
        super.nextLine();
        return this;
    }

    /**
     * Aggiunge un bottone al menu sulla colonna verticale
     *
     * @param button bottone da aggiungere
     */
    public VerticalMenu addButton(MenuButton button) {
        super.addButton(button);
        super.nextLine();
        return this;
    }
}
