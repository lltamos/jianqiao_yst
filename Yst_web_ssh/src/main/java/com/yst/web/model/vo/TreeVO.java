package com.yst.web.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yst.web.model.Menu;

public class TreeVO implements Serializable {
	private int id;
	private String text;
	private Map<String, Object> state;
	private Menu a_attr;

	private List<TreeVO> children = new ArrayList<TreeVO>();

	public TreeVO(Menu menu) {
		this.id = menu.getMenu_id();
		this.text = menu.getName();
		this.a_attr = menu;
		for (Menu child : menu.getChildren()) {
			this.children.add(new TreeVO(child));
		}
	}

	public TreeVO(Menu menu, Map<String, Object> map) {
		this.id = menu.getMenu_id();
		this.text = menu.getName();
		this.a_attr = menu;
		for (Menu child : menu.getChildren()) {
			if (map.get(child.getMenu_id().toString()) != null ) {
				this.children.add(new TreeVO(child, map));
			}else if(child.getChildren().size()!=0){
				boolean flg =false;
				for (Menu child1 : child.getChildren()) {
					if(map.get(child1.getMenu_id().toString())!=null){
						flg=true;
						break;
					}
				}
				if(flg){
					this.children.add(new TreeVO(child, map));
				}
			}
		}
	}

	public TreeVO(Menu menu, Map<String, Object> map, String act) {
		this.id = menu.getMenu_id();
		this.text = menu.getName();
		this.a_attr = menu;
		if (map.get(menu.getMenu_id().toString()) != null) {
			Map<String, Object> stateMap = new HashMap<String, Object>();
			stateMap.put("selected", true);
			this.state = stateMap;
		}
		for (Menu child : menu.getChildren()) {
			this.children.add(new TreeVO(child, map, act));
		}
	}

	public Map<String, Object> getState() {
		return state;
	}

	public void setState(Map<String, Object> state) {
		this.state = state;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<TreeVO> getChildren() {
		return children;
	}

	public void setChildren(List<TreeVO> children) {
		this.children = children;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Menu getA_attr() {
		return a_attr;
	}

	public void setA_attr(Menu a_attr) {
		this.a_attr = a_attr;
	}

}
