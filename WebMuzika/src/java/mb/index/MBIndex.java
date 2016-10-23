/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb.index;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Filip
 */
@ManagedBean
@RequestScoped
public class MBIndex {

    List<String> images;

    @PostConstruct
    public void init(){
        images = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            images.add("photo" + i + ".jpg");
        }
    }
    
    public List<String> getImages() {
        return images;
    }

}
