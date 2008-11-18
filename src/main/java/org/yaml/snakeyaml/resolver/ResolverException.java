/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package org.yaml.snakeyaml.resolver;

import org.yaml.snakeyaml.error.YAMLException;

/**
 * @see PyYAML 3.06 for more information
 */
class ResolverException extends YAMLException {
    private static final long serialVersionUID = -4199504387416260972L;

    public ResolverException(final String msg) {
        super(msg);
    }

    public ResolverException(final Throwable thr) {
        super(thr);
    }
}