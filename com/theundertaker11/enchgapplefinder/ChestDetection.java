/*    */ package com.theundertaker11.enchgapplefinder;
/*    */ 
/*    */ import java.io.BufferedWriter;
/*    */ import java.io.FileWriter;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
/*    */ import net.minecraft.enchantment.EnchantmentHelper;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.EntityMinecartContainer;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Enchantments;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.tileentity.TileEntityLockableLoot;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber
/*    */ public class ChestDetection
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void WorldTick(TickEvent.WorldTickEvent event) {
/* 27 */     if (event.world.field_72995_K)
/*    */       return; 
/* 29 */     World world = event.world;
/* 30 */     EntityPlayer player = null;
/* 31 */     if (!world.field_73010_i.isEmpty()) {
/* 32 */       player = world.field_73010_i.get(0);
/*    */ 
/*    */ 
/*    */       
/* 36 */       for (TileEntity tile : world.field_147482_g) {
/* 37 */         if (tile instanceof TileEntityLockableLoot) {
/* 38 */           TileEntityLockableLoot lockable = (TileEntityLockableLoot)tile;
/* 39 */           if (lockable.func_184276_b() != null) {
/* 40 */             lockable.func_184281_d(player);
/* 41 */             for (int i = 0; i < lockable.func_70302_i_(); i++) {
/* 42 */               ItemStack stack = lockable.func_70301_a(i);
/* 43 */               if (stack.func_77973_b() == Items.field_151153_ao && stack.func_77952_i() == 1) {
/* 44 */                 writeToFile("Dungeon Chest with ench gapple at: " + lockable.func_174877_v().func_177958_n() + " " + lockable.func_174877_v().func_177956_o() + " " + lockable.func_174877_v().func_177952_p());
/*    */               }
/* 46 */               if (stack.func_77973_b() == Items.field_151134_bR && 
/* 47 */                 EnchantmentHelper.func_77506_a(Enchantments.field_185296_A, stack) > 0) {
/* 48 */                 writeToFile("Dungeon Chest with Mending Book: " + lockable.func_174877_v().func_177958_n() + " " + lockable.func_174877_v().func_177956_o() + " " + lockable.func_174877_v().func_177952_p());
/*    */               }
/*    */             } 
/*    */           } 
/*    */         } 
/*    */       } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 60 */       for (Entity entity : world.field_72996_f) {
/* 61 */         if (entity instanceof EntityMinecartContainer) {
/* 62 */           EntityMinecartContainer cart = (EntityMinecartContainer)entity;
/* 63 */           if (cart.func_184276_b() != null) {
/* 64 */             cart.func_184288_f(player);
/* 65 */             for (int i = 0; i < cart.itemHandler.getSlots(); i++) {
/* 66 */               ItemStack stack = cart.itemHandler.getStackInSlot(i);
/* 67 */               if (stack.func_77973_b() == Items.field_151153_ao && stack.func_77952_i() == 1) {
/* 68 */                 writeToFile("Minecart with ench gapple at: " + cart.field_70165_t + " " + cart.field_70163_u + " " + cart.field_70161_v);
/*    */               }
/* 70 */               if (stack.func_77973_b() == Items.field_151134_bR && 
/* 71 */                 EnchantmentHelper.func_77506_a(Enchantments.field_185296_A, stack) > 0) {
/* 72 */                 writeToFile("Minecart with Mending at: " + cart.field_70165_t + " " + cart.field_70163_u + " " + cart.field_70161_v);
/*    */               }
/*    */             } 
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected static void writeToFile(String coords) {
/* 84 */     try(FileWriter fw = new FileWriter("TheUnderTaker11Coords.txt", true); 
/* 85 */         BufferedWriter bw = new BufferedWriter(fw); 
/* 86 */         PrintWriter out = new PrintWriter(bw)) {
/* 87 */       out.println(coords);
/* 88 */     } catch (IOException iOException) {}
/*    */   }
/*    */ }
