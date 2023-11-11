package model;

import java.util.ArrayList;
import java.util.List;

public class Dish
{
    private String m_name;
    private String m_description;
    private List<String> m_ingredients;
    private String m_recipe;

    public Dish(String name)
    {
        m_name = name;
        m_description = "";
        m_ingredients = new ArrayList<>();
        m_recipe = "";
    }

    public Dish(String name, String description, List<String> ingredients, String recipe)
    {
        m_name = name;
        m_description = description;
        m_ingredients = ingredients;
        m_recipe = recipe;
    }

    public String get_name()
    {
        return m_name;
    }

    public void set_name(String m_name)
    {
        this.m_name = m_name;
    }

    public String get_description()
    {
        return m_description;
    }

    public void set_description(String m_description)
    {
        this.m_description = m_description;
    }

    public List<String> get_ingredients()
    {
        return m_ingredients;
    }

    public void set_ingredients(List<String> m_ingredients)
    {
        this.m_ingredients = m_ingredients;
    }

    public String get_recipe()
    {
        return m_recipe;
    }

    public void set_recipe(String m_recipe)
    {
        this.m_recipe = m_recipe;
    }
}
