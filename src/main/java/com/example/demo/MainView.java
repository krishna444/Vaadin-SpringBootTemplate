package com.example.demo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;


@PageTitle("Main")
@Route(value = "")
class MainView extends VerticalLayout{
    private int rubinCount=0,babaCount=0,mummyCount=0;
    public MainView(){

        Button button=new Button("Click here");
        HorizontalLayout hl=new HorizontalLayout();
        Label rubinLabel=new Label("Rubin");
        TextField textFieldRubin=new TextField("");
        Label babaLabel=new Label("Baba");
        TextField textFieldBaba=new TextField("");
        Label mummyLabel=new Label("Mummy");
        TextField textFieldMummy=new TextField("");



        TextArea textArea=new TextArea("");
        button.addClickListener(click->textArea.setLabel("Rubin Paudel"));
        textArea.addKeyDownListener(keydown-> {
            int i=(int)(Math.random()*100);
            rubinCount=i>67?rubinCount+1:rubinCount;
            babaCount=i>33 && i<=67?babaCount+1:babaCount;
            mummyCount=i<=33?mummyCount+1:mummyCount;
            textFieldRubin.setValue(rubinCount+"");
            textFieldBaba.setValue(babaCount+"");
            textFieldMummy.setValue(mummyCount+"");
            String text=i>67?"Rubin Paudel":i>33 && i<=67?"Krishna Paudel":"Mummy";
            textArea.setValue(textArea.getValue() + "\n"+text);
            i++;
        }
        );
        this.add(button,rubinLabel,textFieldRubin,babaLabel,textFieldBaba,mummyLabel,textFieldMummy,textArea);
    }



}