package net.fneifnox.yace.controller.selectionController.stringSelectionDropdown;

import dev.isxander.yacl3.api.utils.Dimension;
import dev.isxander.yacl3.gui.YACLScreen;
import dev.isxander.yacl3.gui.controllers.ControllerWidget;
import dev.isxander.yacl3.gui.controllers.dropdown.AbstractDropdownController;
import dev.isxander.yacl3.gui.controllers.dropdown.AbstractDropdownControllerElement;
import dev.isxander.yacl3.gui.controllers.dropdown.DropdownWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;
import java.util.function.Consumer;

public class YaceStringSelectionDropdownControllerElement extends ControllerWidget<YaceStringSelectionDropdownController> {
    private final ButtonWidget button;
    private PureDropdownWidget dropdownWidget;

    protected final Text modifiedOptionName;
    protected final String optionNameString;

    public YaceStringSelectionDropdownControllerElement(YaceStringSelectionDropdownController control, YACLScreen screen, Dimension<Integer> dim) {
        super(control, screen, dim);
        this.modifiedOptionName = control.option().name().copy().formatted(Formatting.ITALIC);
        this.optionNameString = control.option().name().getString().toLowerCase();

        this.button = ButtonWidget.builder(
                Text.empty(),
                btn -> toggleDropdown()
        ).dimensions(dim.x(), dim.y(), dim.width(), dim.height()).build();
    }

    @Override
    protected int getHoveredControlWidth() {
        return getDimension().width();
    }

    private String getCurrentValue() {
        return control.option().pendingValue();
    }

    private void toggleDropdown() {
        if (dropdownWidget != null) {
            closeDropdown();
        } else {
            openDropdown();
        }
    }

    private void openDropdown() {
        YaceStringSelectionDropdownController dropdownController = this.control;

        var dummyElement = new AbstractDropdownControllerElement<String, String>(dropdownController, screen, getDimension()) {
            {
                this.matchingValues = dropdownController.getAllowedValues();
            }

            @Override
            public List<String> computeMatchingValues() {
                return dropdownController.getAllowedValues();
            }

            @Override
            public String getString(String object) {
                return object;
            }

            @Override
            public void render(DrawContext graphics, int mouseX, int mouseY, float delta) {
            }
        };

        dropdownWidget = new PureDropdownWidget(
                dropdownController,
                screen,
                getDimension(),
                dummyElement,
                dropdownController.getAllowedValues(),
                selectedValue -> {
                    dropdownController.option().requestSet(selectedValue);
                },
                () -> {
                    this.dropdownWidget = null;
                }
        );

        screen.addPopupControllerWidget(dropdownWidget);
    }

    public void closeDropdown() {
        if (dropdownWidget != null) {
            screen.clearPopupControllerWidget();
            dropdownWidget = null;
        }
    }

    @Override
    public void render(DrawContext graphics, int mouseX, int mouseY, float delta) {
        this.button.setX(getDimension().x());
        this.button.setY(getDimension().y());
        this.button.setWidth(getDimension().width());
        this.button.setHeight(getDimension().height());
        this.button.render(graphics, mouseX, mouseY, delta);

        super.render(graphics, mouseX, mouseY, delta);
    }

    protected int getUnhoveredControlWidth() {
        return textRenderer.getWidth(getCurrentValue());
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return this.button.mouseClicked(mouseX, mouseY, button);
    }

    public static class PureDropdownWidget extends DropdownWidget<String> {
        private final Consumer<String> onSelected;
        private final List<String> values;
        private final Runnable onClose;

        public PureDropdownWidget(AbstractDropdownController<String> control, YACLScreen screen, Dimension<Integer> dim, AbstractDropdownControllerElement<String, ?> dropdownElement, List<String> values, Consumer<String> onSelected, Runnable onClose) {
            super(control, screen, dim, dropdownElement);
            this.values = values;
            this.onSelected = onSelected;
            this.onClose = onClose;
        }

        @Override
        public boolean onMouseClicked(double mouseX, double mouseY, int button) {
            if (isMouseOver(mouseX, mouseY)) {
                int index = selectedIndex();
                if (index >= 0 && index < values.size()) {
                    onSelected.accept(values.get(index));
                }
                close();
                return true;
            } else {
                close();
                return false;
            }
        }

        @Override
        public void close() {
            super.close();
            if (onClose != null) {
                onClose.run();
            }
        }
    }
}