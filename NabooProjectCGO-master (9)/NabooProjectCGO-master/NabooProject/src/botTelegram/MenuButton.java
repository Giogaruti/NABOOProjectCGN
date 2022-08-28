package botTelegram;


import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public interface MenuButton {
    InlineKeyboardButton generateInlineKeyboardButton();
}