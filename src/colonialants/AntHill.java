/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package colonialants;

import java.io.Serializable;

/**
 *
 * @author George McDaid
 */
public class AntHill implements Terrain, Serializable{

    String texture;

    public AntHill(String texture) {
        this.texture = texture;
    }
    
    @Override
    public String toString(){
        return "anthill";
    }
    
    @Override
    public String getTexture() {
        return texture;
    }

    @Override
    public void setTexture(String s) {
        texture = s;
    }
    
}
