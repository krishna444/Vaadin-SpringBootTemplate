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

    public MainView() {

        Button button = new Button("Show Result");
        VerticalLayout vl = new VerticalLayout();
        Label rubinLabel = new Label("Rubin");
        TextField textFieldRubin = new TextField("");
        textFieldRubin.setReadOnly(true);
        textFieldRubin.setVisible(false);
        Label babaLabel = new Label("Baba");
        TextField textFieldBaba = new TextField("");
        textFieldBaba.setReadOnly(true);
        textFieldBaba.setVisible(false);
        Label mummyLabel = new Label("Mummy");
        TextField textFieldMummy = new TextField("");
        textFieldMummy.setReadOnly(true);
        textFieldMummy.setVisible(false);
        vl.add(rubinLabel,textFieldRubin,babaLabel,textFieldBaba,mummyLabel,textFieldMummy,button);

        button.addClickListener(event->{textFieldRubin.setVisible(true);textFieldBaba.setVisible(true);textFieldMummy.setVisible(true);});
        TextArea textArea = new TextArea("");
        button.addClickListener(click -> textArea.setLabel("Rubin Paudel"));
        textArea.addKeyDownListener(keydown -> {
                    int i = (int) (Math.random() * 10000);
                    rubinCount = i > 6666 ? rubinCount + 1 : rubinCount;
                    babaCount = i >= 3333 && i <= 6666 ? babaCount + 1 : babaCount;
                    mummyCount = i < 3333 ? mummyCount + 1 : mummyCount;
                    textFieldRubin.setValue(rubinCount + "");
                    textFieldBaba.setValue(babaCount + "");
                    textFieldMummy.setValue(mummyCount + "");
                    String text = i > 6666? "Rubin Paudel" : i >= 3333 && i <= 6666 ? "Krishna Paudel" : "Mummy";
                    textArea.setValue(textArea.getValue() + "\n" + text);
                }
        );
        HorizontalLayout horizontalLayout=new HorizontalLayout(textArea);
        horizontalLayout.setHeight("500px");
        this.add(vl, horizontalLayout);
    }


}