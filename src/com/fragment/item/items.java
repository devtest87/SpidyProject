package com.fragment.item;
public class items{
	
		public String itemtext;
		public int itemImageClicked;
		public int itemImageNonClicked;
		public boolean isClicked = false;
		
		public String getItemtext() {
			return itemtext;
		}
		public void setItemtext(String itemtext) {
			this.itemtext = itemtext;
		}
		public int getItemImageClicked() {
			return itemImageClicked;
		}
		public void setItemImageClicked(int itemImageClicked) {
			this.itemImageClicked = itemImageClicked;
		}
		public int getItemImageNonClicked() {
			return itemImageNonClicked;
		}
		public void setItemImageNonClicked(int itemImageNonClicked) {
			this.itemImageNonClicked = itemImageNonClicked;
		}
		public boolean isClicked() {
			return isClicked;
		}
		public void setClicked(boolean isClicked) {
			this.isClicked = isClicked;
		}
				
		
	}