package com.moreti.kmoretimsscbeerservice.services.inventory;

import java.util.UUID;

public interface BeerInventoryService {

    Integer getOnHandInventory(UUID beerId);
}
