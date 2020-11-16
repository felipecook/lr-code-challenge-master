package com.labregister.api.items.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.labregister.api.core.validation.Entity;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple Item resource to demonstrate Labregister functionality
 */
@JsonIgnoreProperties(value = {"creationDate", "id"}, allowGetters = true)
public class Item implements Entity, Comparable<Item> {

	// TODO create a map that holds creation date as key and value is another map of attributes corresponding to that
	// TODO can we assume that the id is unique to the item?
	// holds the different versions of items, using their date to map to the map of attributes
	//private Map<@NotBlank Date, Map<@NotBlank String, @NotBlank String>> versionedAttributes = new HashMap<>();
	// this will hold the current version of the item, check by date? *(major and minor versions differentiation possibly)
	// private String versionId;
	// update field will have to also be updated with quantity
	// front end: need some way to pull a list of items, people can select from this list the version that they want
	// think about quantity of item, holding value of stock, in update you can change the inventory number
	// date makes more sense to hold history of values of an item
	// customer oriented discussion
	// quantity field?

	private String id;

	@NotBlank
	private String name;

	private Map<@NotBlank String, @NotBlank String> attributes = new HashMap<>();

	private Date creationDate = new Date();

	public Item() {
		// needed for JSON deserialization
	}

	public Item(String name) {
		this.name = name;
	}

	public Item(String name, Map<String, String> attributes) {
		this.name = name;
		this.attributes = attributes;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	/*public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}*/


	@Override
	public int compareTo(Item item) {
		return item.getCreationDate().compareTo(this.getCreationDate());
	}

	/*public Map<Date, Map<String, String>> getVersionedAttributes() {
		return versionedAttributes;
	}

	public void setVersionedAttributes(
			Map<Date, Map<String, String>> versionedAttributes) {
		this.versionedAttributes = versionedAttributes;
	}*/
}
