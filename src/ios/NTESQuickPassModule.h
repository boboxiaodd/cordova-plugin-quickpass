//
//  NTESQuickPassModule.h
//  libWeexDCRichAlert
//
//  Created by 罗礼豪 on 2020/7/6.
//  Copyright © 2020 DCloud. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <Cordova/CDV.h>

NS_ASSUME_NONNULL_BEGIN

@interface NTESQuickPassModule : CDVPlugin

- (void)shouldQuickLogin:(CDVInvokedUrlCommand *)command;

- (void)init:(CDVInvokedUrlCommand *)command;

- (void)setUiConfig:(CDVInvokedUrlCommand *)command;

- (void)preFetchNumber:(CDVInvokedUrlCommand *)command;

- (void)login:(CDVInvokedUrlCommand *)command;

- (void)closeAuthController:(CDVInvokedUrlCommand *)command;

- (void)numberVerify:(CDVInvokedUrlCommand *)command;

@end

NS_ASSUME_NONNULL_END
