package botTelegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class SimpleMenu implements Menu {

    private String text;
    private List<List<MenuButton>> markup = new ArrayList<>();
    private ParseMode parseMode = ParseMode.PLAINTEXT;

    /**
     * Crea un SimpleMenu
     */
    public SimpleMenu() {
    }

    /**
     * Settare il testo che compare nel menu.
     *
     * @param text testo presente nel messaggio del menu
     */
    public SimpleMenu setText(String text) {
        this.text = text;
        return this;
    }

    /**
     * Ritorna la configurazione corrente di parsemode.
     */
    public ParseMode getParseMode() {
        return parseMode;
    }

    /**
     * Setta la parsemode del menu.
     */
    public SimpleMenu setParseMode(ParseMode parseMode) {
        this.parseMode = parseMode;
        return this;
    }


    /**
     * Aggiunge un bottone al menu nella riga orizzontale corrente
     *
     * @param buttonText la scritta presente sul bottone
     * @param action     azione da eseguire quando l'user clicca il bottone
     */
    public SimpleMenu addButton(String buttonText, Callback action) {
        if (markup.isEmpty()) {
            markup.add(new LinkedList<>());
        }
        markup.get(markup.size() - 1).add(new CallbackButton(buttonText, action));
        return this;
    }

    /**
     * Aggiunge un bottone al menu sulla riga orizzontale corrente
     *
     * @param button bottone da aggiungere
     */
    public SimpleMenu addButton(MenuButton button) {
        if (markup.isEmpty()) {
            markup.add(new LinkedList<>());
        }
        markup.get(markup.size() - 1).add(button);
        return this;
    }

    /**
     * Aggiunge una nuova riga alla lista dei bottoni
     */
    public SimpleMenu nextLine() {
        markup.add(new LinkedList<>());
        return this;
    }

    /**
     * Genera un oggetto EditMessageText che può essere eseguito dal bot per modificare il messaggio in un bottone
     *
     * @param message messaggio da modificare
     */
    public EditMessageText generateEditMessage(Message message) {
        return new EditMessageText()
                .setMessageId(message.getMessageId())
                .setChatId(message.getChatId())
                .setText(text)
                .setReplyMarkup(generateMarkup(markup))
                .setParseMode(parseMode.toString());
    }

    /**
     * Genera la pulsantiera del menu compatibile con telegram
     */
    private InlineKeyboardMarkup generateMarkup(List<List<MenuButton>> markup) {
        List<List<InlineKeyboardButton>> returnRows = new LinkedList<>();
        for (List<MenuButton> row : markup) {
            List<InlineKeyboardButton> returnColumns = new LinkedList<>();
            for (MenuButton button : row) {
                returnColumns.add(button.generateInlineKeyboardButton());
            }
            if (!returnColumns.isEmpty()) {
                returnRows.add(returnColumns);
            }
        }
        return new InlineKeyboardMarkup().setKeyboard(returnRows);
    }

    /**
     * Rimuove tutti i bottoni
     */
    public SimpleMenu clearMarkup() {
        markup.clear();
        return this;
    }

    /**
     * Ritorna i markup correnti
     */
    public List<List<MenuButton>> getMarkup() {
        return markup;
    }

    /**
     * Sostituisce i correnti markup.
     */
    public SimpleMenu setMarkup(List<List<MenuButton>> markup) {
        this.markup = markup;
        return this;
    }

    /**
     * Genera un oggetto SendMessage da mandare nella chat telegram
     *
     * @param chatId destinazione dell oggetto SendMessage
     */
    public SendMessage generateSendMessage(Long chatId) {
        return generateSendMessage(String.valueOf(chatId))
                .setParseMode(parseMode.toString());
    }

    /**
     * Genera un oggetto SendMessage da mandare nella chat telegram
     *
     * @param chatId destinazione dell oggetto SendMessage
     */
    public SendMessage generateSendMessage(String chatId) {
        return generateSendMessage()
                .setChatId(chatId)
                .setParseMode(parseMode.toString());
    }

}