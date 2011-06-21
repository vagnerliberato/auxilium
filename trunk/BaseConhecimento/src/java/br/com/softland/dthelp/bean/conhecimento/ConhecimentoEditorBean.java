package br.com.softland.dthelp.bean.conhecimento;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "conhecimentoEditorBean")
public class ConhecimentoEditorBean {  
  
    private String value;  
  
    public String getValue() {  
        return value;  
    }  
  
    public void setValue(String value) {  
        this.value = value;  
    }  
} 