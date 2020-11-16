package com.labregister.api.items.controller;

import com.google.common.base.Preconditions;
import com.labregister.api.core.creation.EntityCreatedResponse;
import com.labregister.api.core.updated.EntityUpdatedResponse;
import com.labregister.api.items.domain.Item;
import com.labregister.api.items.service.ItemService;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ItemController {

	private static final String RESOURCE_ITEM = "/items/{id}";

	private final ItemService itemService;

	public ItemController(ItemService itemService) {
		Preconditions.checkArgument(itemService != null);
		this.itemService = itemService;
	}

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Item> getItems() {
		return itemService.getItems();
	}

	@RequestMapping(value = "/items", method = RequestMethod.POST)
	public EntityCreatedResponse<Item> createItem(@RequestBody Item request) {
		Item created = itemService.createItem(request);
		return new EntityCreatedResponse<>(created, getItemLocation(created));
	}

	// TODO write PUT (update) method here
	@RequestMapping(value = "/items/{itemId}", method = RequestMethod.PUT)
	public EntityUpdatedResponse<Item> updateItem(@RequestBody Item request, @PathVariable String itemId){
		Item updated = itemService.updateItem(request, itemId);
		return new EntityUpdatedResponse<>(updated, getItemLocation(updated));
		// Calls THE SERVICE CALSS AND UPDATES THE ITEM
		// RETURNS AN ENTITYUPDATEDRESPONSE<>
	}



	private URI getItemLocation(Item item) {
		UriTemplate itemLocation = new UriTemplate(RESOURCE_ITEM);
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("id", item.getId());
		return itemLocation.expand(uriVariables);
	}

	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	private void notFound(){}


}
