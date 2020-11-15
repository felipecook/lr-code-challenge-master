package com.labregister.api.items.service;

import com.labregister.api.core.validation.EntityValidator;
import com.labregister.api.items.domain.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

	// This is the DB
	private List<Item> items;

	private EntityValidator entityValidator;

	public ItemServiceImpl(EntityValidator entityValidator) {
		this.entityValidator = entityValidator;
		this.items = new ArrayList<>();
	}

	@Override
	public Item createItem(Item request) {
		entityValidator.validateCreate(request);
		request.setId(UUID.randomUUID().toString());
		request.setCreationDate(new Date());
		return save(request);
	}

	@Override
	public List<Item> getItems() {
		return this.items.stream()
		                 .sorted()
		                 .collect(Collectors.toList());
	}

	@Override
	public List<Item> updateItems(List<Item> items) {
		return null;
	}

	@Override
	public Item updateItem(Item request) {

		/*
		* - Validate update (go into EntityValidator.validateUpdate and do validation)
		* - Find teh Item object in the items list using the ID
		* - Update with the new attributes/arguments
		* - Return the updated item
		*
		* */

		// need to check how to correctly call the two update validations and their purpose
		entityValidator.validateUpdate(request);

	/*	for (Item item : this.items) {
			if (request.getId().equals(item.getId())) {
				;
			}
		}*/

		for (int i = 0; i < items.size(); i++) {
			if (request.getName().equals(items.get(i).getName())) {
				items.remove(i);
			}
		}
		items.add(request);
		// delete the old one and replace with new requested one.

		return request;
	}

	@Override
	public void deleteAllItems() {
		this.items.clear();
	}

	private Item save(Item item) {
		this.items.add(item);
		return item;
	}
}
