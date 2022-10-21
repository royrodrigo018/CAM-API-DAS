package com.dxc.imda.cam.common.constant;


public class Enums {
	
	public enum AppName {NORS, NEUPC, DAS, IGMS};
	
	public enum ResourceType {USER, GROUP};
	
	public enum SortOrder {ASC, DESC};
	
	public enum SortedBy {
		USER_ID ("userId"), 
		USER_NAME ("userName"), 
		GROUP_ID ("groupId"), 
		GROUP_NAME ("groupName");

		private final String sortedBy;
	
		SortedBy(String sortedBy) {
			this.sortedBy = sortedBy;
		}

		public String getSortedBy() {
			return sortedBy;
		}		
	};	
}
