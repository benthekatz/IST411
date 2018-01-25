/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

/**
 *
 * @author Ben
 */
public class HelloView extends ServerView{
    @Override
    public String makeHTML(){
        return "<html><body>Hello World!</body></html>";
    }
}
