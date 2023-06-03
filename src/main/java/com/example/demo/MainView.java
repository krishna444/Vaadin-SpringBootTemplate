package com.example.demo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Main")
@Route(value = "")
class MainView extends VerticalLayout {
    private int rubinCount = 0, babaCount = 0, mummyCount = 0;
    private boolean showResult = false;

    public MainView() {
        Button button = new Button("Show Result");
        VerticalLayout vl = new VerticalLayout();
        vl.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        vl.setWidthFull();
        Label rubinLabel = new Label("Rubin");
        TextField textFieldRubin = new TextField("");
        textFieldRubin.setReadOnly(true);
        textFieldRubin.setVisible(false);
        HorizontalLayout rubinHL = new HorizontalLayout(rubinLabel, textFieldRubin);
        Label babaLabel = new Label("Baba");
        TextField textFieldBaba = new TextField("");
        textFieldBaba.setReadOnly(true);
        textFieldBaba.setVisible(false);
        HorizontalLayout babaHL = new HorizontalLayout(babaLabel, textFieldBaba);
        Label mummyLabel = new Label("Mummy");
        TextField textFieldMummy = new TextField("");
        textFieldMummy.setReadOnly(true);
        textFieldMummy.setVisible(false);
        HorizontalLayout mummyHL = new HorizontalLayout(mummyLabel, textFieldMummy);
        vl.add(rubinHL, babaHL, mummyHL, button);

        VerticalLayout resultHL = new VerticalLayout();
        resultHL.setWidthFull();
        resultHL.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        Label winnerLabel = new Label("Winner: ");
        TextField winnerTextField = new TextField("");
        winnerTextField.getElement().getStyle().set("color", "red");
        winnerTextField.getElement().getStyle().set("font-size", "26px");
        resultHL.add(winnerLabel, winnerTextField);
        vl.add(resultHL);

        button.addClickListener(event -> {
            textFieldRubin.setVisible(!this.showResult);
            textFieldBaba.setVisible(!this.showResult);
            textFieldMummy.setVisible(!this.showResult);
            this.showResult = !this.showResult;
            button.setText(this.showResult ? "Hide Result" : "Show Result");
        });
        TextArea textArea = new TextArea("");
        textArea.setHeight("450px");
        textArea.setWidthFull();
        button.addClickListener(click -> textArea.setLabel("History"));
        textArea.addKeyDownListener(keydown -> {
                    int i = (int) (Math.random() * 10000);
                    String result = i > 6666 ? "R" : i < 3333 ? "M" : "B";
                    String winner = "";
                    switch (result) {
                        case "R":
                            rubinCount++;
                            winner = "Rubin";
                            break;
                        case "M":
                            mummyCount++;
                            winner = "Mummy";
                            break;
                        default:
                            babaCount++;
                            winner = "Baba";
                    }
                    textFieldRubin.setValue(rubinCount + "");
                    textFieldBaba.setValue(babaCount + "");
                    textFieldMummy.setValue(mummyCount + "");
                    textArea.setValue(textArea.getValue() + "\t" + winner);
                    if (rubinCount > babaCount && rubinCount > mummyCount) {
                        winnerTextField.setValue("Rubin");
                    } else if (mummyCount > rubinCount && mummyCount > babaCount) {
                        winnerTextField.setValue("Mummy");
                    } else {
                        winnerTextField.setValue("Baba");
                    }
                }
        );
        VerticalLayout infoVerticalLayout = new VerticalLayout(textArea);
        infoVerticalLayout.setHorizontalComponentAlignment(Alignment.CENTER, textArea);
        infoVerticalLayout.setWidthFull();
        //infoVerticalLayout.setMaxHeight("500px");
        this.add(vl, infoVerticalLayout);
    }


}