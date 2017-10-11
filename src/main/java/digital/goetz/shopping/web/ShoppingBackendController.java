package digital.goetz.shopping.web;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import digital.goetz.shopping.data.Item;
import digital.goetz.shopping.data.ItemRepository;
import digital.goetz.shopping.data.State;

@RestController
@RequestMapping("/api")
public class ShoppingBackendController {

	@Autowired
	ItemRepository itemRepository;

	@RequestMapping(path = "/list/all", method = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<List<Item>> getAll() {
		List<Item> list = new ArrayList<>();
		itemRepository.findAll().forEach(item -> list.add(item));
		return ResponseEntity.ok(list);
	}

	@RequestMapping(path = "/list/buy", method = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<List<Item>> getBuy() {
		List<Item> list = new ArrayList<>();
		itemRepository.findByState(State.BUY).forEach(item -> list.add(item));
		return ResponseEntity.ok(list);
	}

	@RequestMapping(path = "/list/bought", method = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<List<Item>> getBought() {
		List<Item> list = new ArrayList<>();
		itemRepository.findByState(State.BOUGHT).forEach(item -> list.add(item));
		return ResponseEntity.ok(list);
	}

	@RequestMapping(path = "/item", method = RequestMethod.POST)
	@ResponseBody
	ResponseEntity<Item> addItem(@RequestBody Item item) {
		if (item != null && item.getName() != null) {
			Date now = new Date();
			item.setCreated(now);
			item.setUpdated(now);
			item.setState(State.BUY);
			Item save = itemRepository.save(item);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(save.getId()).toUri();
			return ResponseEntity.created(uri).body(save);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(path = "/item/{id}", method = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<Item> getItem(@PathVariable Long id) {
		if (id != null) {
			Item found = itemRepository.findOne(id);
			if (found != null) {
				return ResponseEntity.ok(found);
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(path = "/item/{id}/bought", method = RequestMethod.PUT)
	@ResponseBody
	ResponseEntity<Item> buyItem(@PathVariable Long id) {
		if (id != null) {
			Item found = itemRepository.findOne(id);
			if (found != null) {
				found.setState(State.BOUGHT);
				found.setUpdated(new Date());
				Item saved = itemRepository.save(found);
				return ResponseEntity.accepted().body(saved);
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
