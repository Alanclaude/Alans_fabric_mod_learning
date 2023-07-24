package fan.alanmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("alanslearningmod");
	public static final String MODID = "alanmod";
	public static final Item MY_FRIST_ITEM = new MyFristItemClass(new FabricItemSettings().fireproof());
	public static final Item MY_SECOND_ITEM = new MyFristItemClass(new FabricItemSettings().fireproof());
	private static final RegistryKey<ItemGroup> ITEM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(MODID, "test_group"));

	@Override
	public void onInitialize() {

		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		Registry.register(Registries.ITEM, new Identifier(MODID, "my_first_item"), MY_FRIST_ITEM);
		Registry.register(Registries.ITEM, new Identifier(MODID, "my_second_item"), MY_SECOND_ITEM);
		/*这里是注册一个新物品
		命名空间是"alanmod" 名字是"my_frist_item"
		 */
		Registry.register(Registries.ITEM_GROUP, ITEM_GROUP, FabricItemGroup.builder()
				.displayName(Text.translatable("alanmod.test_group"))
				.icon(() -> new ItemStack(MY_FRIST_ITEM))
				.entries((context, entries) -> entries.add(MY_FRIST_ITEM))
				.build()
		);
		/*这里是注册新的物品组
		  在1.20版本注册方法有更改,
		  并非fabric官方中文wiki的示例
		  具体以英文版为准, 这里是按照idea查看的文档写的
		 */

		LOGGER.info("Hello Fabric world!");
		ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP).register(content -> content.add(MY_FRIST_ITEM));
		ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP).register(content -> content.add(MY_SECOND_ITEM));
	}
}