package controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class LimitedTextField extends TextField {
    private IntegerProperty maxLength = new SimpleIntegerProperty(this,
            "maxLength", -1);
    private StringProperty restrict = new SimpleStringProperty(this, "restrict");

    public LimitedTextField() {
        textProperty().addListener(new ChangeListener<String>() {
            private boolean ignore;

            @Override
            public void changed(
                    ObservableValue<? extends String> observableValue,
                    String s, String s1) {
                if (ignore || s1 == null)
                    return;
                if (maxLength.get() > -1 && s1.length() > maxLength.get()) {
                    ignore = true;
                    setText(s1.substring(0, maxLength.get()));
                    ignore = false;
                }
                if (restrict.get() != null && !restrict.get().equals("")
                        && !s1.matches(restrict.get() + "*")) {
                    ignore = true;
                    setText(s);
                    ignore = false;
                }
            }
        });
    }

    /**
     * The max length property.
     *
     * @return The max length property.
     */
    public IntegerProperty maxLengthProperty() {
        return maxLength;
    }

    /**
     * Gets the max length of the text field.
     *
     * @return The max length.
     */
    public int getMaxLength() {
        return maxLength.get();
    }

    /**
     * Sets the max length of the text field.
     *
     * @param maxLength
     *            The max length.
     */
    public void setMaxLength(int maxLength) {
        this.maxLength.set(maxLength);
    }

    /**
     * The restrict property.
     *
     * @return The restrict property.
     */
    public StringProperty restrictProperty() {
        return restrict;
    }

    /**
     * Gets a regular expression character class which restricts the user input.
     *
     *
     * @return The regular expression.
     * @see #getRestrict()
     */
    public String getRestrict() {
        return restrict.get();
    }

    /**
     * Sets a regular expression character class which restricts the user input.
     *
     * E.g. [0-9] only allows numeric values.
     *
     * @param restrict
     *            The regular expression.
     */
    public void setRestrict(String restrict) {
        this.restrict.set(restrict);
    }

    /**
     * Sets a predefined regular expression character class which restricts the
     * user input.
     *
     * @Param setRestrict("[A-Za-z0-9._-]")
     *
     */
    public void setStandardField() {
        this.setRestrict("[A-Za-z0-9._-]");
    }

    /**
     * Sets a predefined regular expression character class which restricts the
     * user input.
     *
     * @Param setRestrict("[A-Za-z0-9 ._-]")
     *
     */
    public void setStandardFieldwSpace() {
        this.setRestrict("[A-Za-z0-9 ._-]");
    }

    /**
     * Sets a predefined regular expression character class which restricts the
     * user input.
     *
     * @Param setRestrict("[A-Za-z ._-]")
     *
     */
    public void setCharsOnlyFieldwSpace() {
        this.setRestrict("[A-Za-z ._-]");
    }

    /**
     * Sets a predefined regular expression character class which restricts the
     * user input.
     *
     * @Param setRestrict("[A-Za-z._-]")
     *
     */
    public void setCharsOnlyField() {
        this.setRestrict("[A-Za-z._-]");
    }

    /**
     * Sets a predefined regular expression character class which restricts the
     * user input.
     *
     * @Param setRestrict("[A-Za-z0-9@._-]")
     *
     */
    public void setEmailField() {
        this.setRestrict("[A-Za-z0-9@._-]");
    }

    /**
     * Sets a predefined regular expression character class which restricts the
     * user input.
     *
     * @Param setRestrict("[0-9.]")
     *
     */
    public void setIntegerField() {
        this.setRestrict("[0-9]");
    }

    /**
     * Sets a predefined regular expression character class which restricts the
     * user input.
     *
     * @Param setRestrict("[0-9.]")
     *
     */
    public void setFloatField() {
        this.setRestrict("[0-9.]");
    }

    /**
     * Sets a predefined regular expression character class which restricts the
     * user input.
     *
     * @Param setRestrict("[0-9-/]")
     * @Param setMaxLength(10)
     *
     */
    public void setDateField() {
        this.setRestrict("[0-9-/]");
        this.setMaxLength(10);
    }

    /**
     * Sets a predefined regular expression character class which restricts the
     * user input.
     *
     * @Param setRestrict("[0-9-]")
     * @Param setMaxLength(9)
     *
     *
     */
    public void setSSNField() {
        this.setRestrict("[0-9]");
        this.setMaxLength(9);
        this.setTooltip(new Tooltip("No dashes."));
    }

    /**
     * Sets a predefined regular expression character class which restricts the
     * user input.
     *
     * @Param setRestrict("[0-9]")
     * @Param setMaxLength(3)
     *
     */
    public void setPhoneCountryCodeField() {
        this.setRestrict("[0-9]");
        this.setMaxLength(3);
    }

    /**
     * Sets a predefined regular expression character class which restricts the
     * user input.
     *
     * @Param setRestrict("[0-9]")
     * @Param setMaxLength(3)
     *
     */
    public void setPhoneAreaCodeField() {
        this.setRestrict("[0-9]");
        this.setMaxLength(3);
    }

    /**
     * Sets a predefined regular expression character class which restricts the
     * user input.
     *
     * @Param setRestrict("[0-9]")
     * @Param setMaxLength(7)
     *
     */
    public void setPhoneNumberField() {
        this.setRestrict("[0-9]");
        this.setMaxLength(7);
        this.setTooltip(new Tooltip("No dashes."));
    }

    /**
     * Sets a predefined regular expression character class which restricts the
     * user input.
     *
     * @Param setRestrict("[A-Za-z0-9]")
     * @Param setMaxLength(5)
     *
     */
    public void setZipcodeField() {
        this.setRestrict("[A-Za-z0-9]");
        this.setMaxLength(5);
    }

    /**
     * Sets a predefined regular expression character class which restricts the
     * user input.
     *
     * @Param setRestrict("[0-9.]")
     * @Param setMaxLength(15)
     *
     */
    public void setIPAddressField() {
        this.setRestrict("[0-9.]");
        this.setMaxLength(15);
    }

    /**
     * Sets a predefined regular expression character class which restricts the
     * user input.
     *
     * @Param setRestrict("[0-9]")
     * @Param setMaxLength(4)
     *
     */
    public void setLogonField() {
        this.setRestrict("[0-9]");
        this.setMaxLength(4);
    }

    /**
     * Sets a predefined regular expression character class which restricts the
     * user input.
     *
     * @Param setRestrict("[0-9]")
     * @Param setMaxLength(13)
     *
     */
    public void setUPCField() {
        this.setRestrict("[0-9]");
        this.setMaxLength(13);
    }
}