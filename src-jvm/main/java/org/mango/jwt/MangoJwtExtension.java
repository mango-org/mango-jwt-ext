package org.mango.jwt;

import org.mango.jwt.classes.JWT;
import php.runtime.env.CompileScope;
import php.runtime.ext.support.Extension;

public class MangoJwtExtension extends Extension {
    public static final String NS = "php\\jwt";
    
    @Override
    public Status getStatus() {
        return Status.EXPERIMENTAL;
    }
    
    @Override
    public void onRegister(CompileScope scope) {
        registerClass(scope, JWT.class);
    }
}
