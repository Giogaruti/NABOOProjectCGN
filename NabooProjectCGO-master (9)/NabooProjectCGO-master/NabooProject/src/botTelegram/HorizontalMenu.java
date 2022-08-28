package botTelegram;

import application.Callback;
import application.HorizontalMenu;
import application.MenuButton;
import application.SimpleMenu;

public class HorizontalMenu extends SimpleMenu {

	 /**
     * Aggiunge un bottone al menu nella riga orizzontale
     *
     * @param buttonText la scritta presente sul bottone
     * @param action     azione da eseguire quando l'user clicca il bottone
     */
    @Override
    public HorizontalMenu addButton(String buttonText, Callback action) {
        super.addButton(buttonText, action);
        return this;
    }

    /**
     * Aggiunge un bottone al menu sulla riga orizzontale
     *
     * @param button bottone da aggiungere
     */
    public HorizontalMenu addButton(MenuButton button) {
        super.addButton(button);
        return this;
    }
}