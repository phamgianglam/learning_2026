package com.store.api.service;

import com.store.api.model.Item;
import com.store.api.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     * Get all items
     *
     * @return List of all items
     */
    @Transactional(readOnly = true)
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    /**
     * Get item by ID
     *
     * @param id the item ID
     * @return Optional containing the item if found
     */
    @Transactional(readOnly = true)
    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    /**
     * Create a new item
     *
     * @param item the item to create
     * @return the created item
     */
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    /**
     * Update an existing item
     *
     * @param id   the item ID
     * @param item the updated item data
     * @return the updated item
     * @throws IllegalArgumentException if item not found
     */
    public Item updateItem(Long id, Item item) {
        if (!itemRepository.existsById(id)) {
            throw new IllegalArgumentException("Item not found with id: " + id);
        }
        item.setId(id);
        return itemRepository.save(item);
    }

    /**
     * Delete an item
     *
     * @param id the item ID
     * @throws IllegalArgumentException if item not found
     */
    public void deleteItem(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new IllegalArgumentException("Item not found with id: " + id);
        }
        itemRepository.deleteById(id);
    }
}
